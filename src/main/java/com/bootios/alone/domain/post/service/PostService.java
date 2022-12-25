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

    Post newPost = mapCreateRequestToEntity(postCreateRequest, foundCreator);

    Post savedPost = postRepository.save(newPost);
    return mapPostEntityToPostInfo(savedPost);
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
    return mapPostEntityToPostInfo(savedPost);
  }

  //  @Transactional
  public void deletePost(Long id) {

    Post foundPost = postRepository.findPostById(id).orElseThrow(NotFoundPostEntityException::new);
    foundPost.deletePost();
    postRepository.save(foundPost);
  }

  public PostInfo getPostDetail(Long id) {

    Post foundPost = postRepository.findPostById(id).orElseThrow(NotFoundPostEntityException::new);
    return mapPostEntityToPostInfo(foundPost);
  }

  private Post mapCreateRequestToEntity(
      PostCreateRequest postCreateRequest, User foundCreator) {
    return Post.builder()
        .title(postCreateRequest.getTitle())
        .creator(foundCreator)
        .startAt(postCreateRequest.getStartAt())
        .limitMember(postCreateRequest.getLimitMember())
        .foodCategory(postCreateRequest.getFoodCategory())
        .build();
  }

  private PostInfo mapPostEntityToPostInfo(Post post) {
    return PostInfo.builder()
        .title(post.getTitle())
        .creatorName(post.getCreator().getKakaoName())
        .startAt(post.getStartAt())
        .limitMember(post.getLimitMember())
        .foodCategory(post.getFoodCategory())
        .createdAt(post.getCreatedAt())
        .build();
  }
}
