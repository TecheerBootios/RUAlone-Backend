package com.bootios.alone.domain.post.service;

import com.bootios.alone.domain.post.domain.entity.Post;
import com.bootios.alone.domain.post.domain.repository.PostRepository;
import com.bootios.alone.domain.post.dto.PostCreateRequest;
import com.bootios.alone.domain.post.dto.PostInfo;
import com.bootios.alone.domain.user.entity.User;
import com.bootios.alone.domain.user.exception.NotFoundUserEntityException;
import com.bootios.alone.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {

  private final UserRepository userRepository;
  private final PostRepository postRepository;

  public PostInfo createPost(PostCreateRequest postCreateRequest) {
    User foundCreator =
        userRepository
            .findUserById(postCreateRequest.getCreatorId())
            .orElseThrow(NotFoundUserEntityException::new);

    Post newPost =
        Post.builder()
            .title(postCreateRequest.getTitle())
            .creator(foundCreator)
            .startAt(postCreateRequest.getStartAt())
            .limitMember(postCreateRequest.getLimitMember())
            .foodCategory(postCreateRequest.getFoodCategory())
            .build();

    Post savedPost = postRepository.save(newPost);
    return PostInfo.builder()
        .title(savedPost.getTitle())
        .creatorName(foundCreator.getNickname())
        .startAt(savedPost.getStartAt())
        .limitMember(savedPost.getLimitMember())
        .foodCategory(savedPost.getFoodCategory())
        .createdAt(savedPost.getCreatedAt())
        .build();
  }
}
