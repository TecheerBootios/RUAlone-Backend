package com.bootios.alone.domain.post.service;

import com.bootios.alone.domain.post.domain.entity.Post;
import com.bootios.alone.domain.post.domain.repository.PostRepository;
import com.bootios.alone.domain.post.dto.PostCreateRequest;
import com.bootios.alone.domain.post.dto.PostInfo;
import com.bootios.alone.domain.post.dto.PostUpdateRequest;
import com.bootios.alone.domain.post.exception.NotFoundPostEntityException;
import com.bootios.alone.domain.post.exception.OnlyCreatorUpdatePostException;
import com.bootios.alone.domain.user.User;
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
    Long creatorId = postCreateRequest.getCreatorId();
    User foundCreator =
        userRepository.findUserById(creatorId).orElseThrow(NotFoundUserEntityException::new);

    Post newPost =
        Post.builder()
            .title(postCreateRequest.getTitle())
            .creator(foundCreator)
            .startAt(postCreateRequest.getStartAt())
            .limitMember(postCreateRequest.getLimitMember())
            .foodCategory(postCreateRequest.getFoodCategory())
            .build();

    Post savedPost = postRepository.save(newPost);
    return mapPostEntityToPostInfo(savedPost);
  }

  private PostInfo mapPostEntityToPostInfo(Post savedPost) {
    return PostInfo.builder()
        .title(savedPost.getTitle())
        .creatorName(savedPost.getCreator().getKakaoName())
        .startAt(savedPost.getStartAt())
        .limitMember(savedPost.getLimitMember())
        .foodCategory(savedPost.getFoodCategory())
        .createdAt(savedPost.getCreatedAt())
        .build();
  }

  public PostInfo updatePost(PostUpdateRequest postUpdateRequest) {
    User foundCreator =
        userRepository
            .findUserById(postUpdateRequest.getCreatorId())
            .orElseThrow(NotFoundUserEntityException::new);

    Post foundPost =
        postRepository
            .findPostById(postUpdateRequest.getPostId())
            .orElseThrow(NotFoundPostEntityException::new);
    //    NotFoundPostEntityException::new
    // == throw new NotFoundPostEntityException();

    if (!foundPost.getCreator().equals(foundCreator)) {
      throw new OnlyCreatorUpdatePostException();
    }

    foundPost.update(postUpdateRequest);

    Post savedPost = postRepository.save(foundPost);
    return PostInfo.builder()
        .title(savedPost.getTitle())
        .creatorName(foundCreator.getKakaoName())
        .startAt(savedPost.getStartAt())
        .limitMember(savedPost.getLimitMember())
        .foodCategory(savedPost.getFoodCategory())
        .createdAt(savedPost.getCreatedAt())
        .build();
  }

  //  @Transactional
  public void deletePost(Long id) { // 삭제하고 싶은 post id

    Post foundPost = postRepository.findPostById(id).orElseThrow(NotFoundPostEntityException::new);
    foundPost.deletePost();
    postRepository.save(foundPost);
  }

  public PostInfo getPostDetail(Long id) {

    Post foundPost = postRepository.findPostById(id).orElseThrow(NotFoundPostEntityException::new);

    return PostInfo.builder()
        .title(foundPost.getTitle())
        .creatorName(foundPost.getCreator().getKakaoName())
        .startAt(foundPost.getStartAt())
        .limitMember(foundPost.getLimitMember())
        .foodCategory(foundPost.getFoodCategory())
        .createdAt(foundPost.getCreatedAt())
        .build();
  }
}
