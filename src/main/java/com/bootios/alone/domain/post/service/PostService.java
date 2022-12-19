package com.bootios.alone.domain.post.service;

import com.bootios.alone.domain.post.domain.entity.Post;
import com.bootios.alone.domain.post.domain.repository.PostRepository;
import com.bootios.alone.domain.post.dto.PostCreateRequest;
import com.bootios.alone.domain.post.dto.PostInfo;
import com.bootios.alone.domain.users.domain.entity.Users;
import com.bootios.alone.domain.users.domain.exception.NotFoundUserEntityException;
import com.bootios.alone.domain.users.domain.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PostService {

  private final UsersRepository usersRepository;
  private final PostRepository postRepository;

  public PostInfo createPost(PostCreateRequest postCreateRequest) {
    Users foundCreator =
        usersRepository
            .findUsersById(postCreateRequest.getCreatorId())
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
        .creatorName(foundCreator.getName())
        .startAt(savedPost.getStartAt())
        .limitMember(savedPost.getLimitMember())
        .foodCategory(savedPost.getFoodCategory())
        .phoneNumber(foundCreator.getPhoneNumber())
        .build();
  }
}
