package com.bootios.alone.domain.post.service;

import com.bootios.alone.domain.post.dto.PostCreateRequest;
import com.bootios.alone.domain.post.dto.PostInfo;
import com.bootios.alone.domain.post.dto.PostUpdateRequest;
import com.bootios.alone.domain.post.entity.Post;
import com.bootios.alone.domain.post.exception.CNotFoundPostEntityException;
import com.bootios.alone.domain.post.exception.COnlyCreatorUpdatePostException;
import com.bootios.alone.domain.post.repository.PostRepository;
import com.bootios.alone.domain.user.entity.User;
import com.bootios.alone.domain.user.exception.CUserNotFoundException;
import com.bootios.alone.domain.user.repository.UserRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostService {

  private final UserRepository userRepository;
  private final PostRepository postRepository;

  @Transactional
  public PostInfo createPost(PostCreateRequest postCreateRequest) {
    Long creatorId = postCreateRequest.getCreatorId();
    User foundCreator =
        userRepository.findUserById(creatorId).orElseThrow(CUserNotFoundException::new);

    Post newPost = mapCreateRequestToEntity(postCreateRequest, foundCreator);

    Post savedPost = postRepository.save(newPost);
    return mapPostEntityToPostInfo(savedPost);
  }

  @Transactional
  public PostInfo updatePost(PostUpdateRequest postUpdateRequest) {
    User foundCreator =
        userRepository
            .findUserById(postUpdateRequest.getCreatorId())
            .orElseThrow(CUserNotFoundException::new);

    Post foundPost =
        postRepository
            .findPostById(postUpdateRequest.getPostId())
            .orElseThrow(CNotFoundPostEntityException::new);
    //    NotFoundPostEntityException::new
    // == throw new NotFoundPostEntityException();

    if (!foundPost.getCreator().equals(foundCreator)) {
      throw new COnlyCreatorUpdatePostException();
    }

    foundPost.update(postUpdateRequest);

    Post savedPost = postRepository.save(foundPost);
    return mapPostEntityToPostInfo(savedPost);
  }

  //  @Transactional
  public void deletePost(Long id) {

    Post foundPost = postRepository.findPostById(id).orElseThrow(CNotFoundPostEntityException::new);
    foundPost.deletePost();
    postRepository.save(foundPost);
  }

  @Transactional(readOnly = true)
  public PostInfo getPostDetail(Long id) {

    Post foundPost = postRepository.findPostById(id).orElseThrow(CNotFoundPostEntityException::new);
    return mapPostEntityToPostInfo(foundPost);
  }

  @Transactional(readOnly = true)
  public List<PostInfo> getPostListByPagination(int page, int size) {
    PageRequest pageRequest = PageRequest.of(page, size);
    return postRepository.findPostWithPagination(pageRequest)
            .stream()
            .map(this::mapPostEntityToPostInfo)
            .collect(Collectors.toList());
  }

  @Transactional(readOnly = true)
  public List<PostInfo> searchPostListWithTitleByPagination(int page, int size, String keyword) {
    PageRequest pageRequest = PageRequest.of(page, size);
    return postRepository.findContainingTitlePostWithPagination(pageRequest, keyword)
            .stream()
            .map(this::mapPostEntityToPostInfo)
            .collect(Collectors.toList());
  }

  private Post mapCreateRequestToEntity(PostCreateRequest postCreateRequest, User foundCreator) {
    return Post.builder()
        .title(postCreateRequest.getTitle())
        .chatUrl(postCreateRequest.getChatUrl())
        .creator(foundCreator)
        .startAt(postCreateRequest.getStartAt())
        .limitMember(postCreateRequest.getLimitMember())
        .foodCategory(postCreateRequest.getFoodCategory())
        .build();
  }

  private PostInfo mapPostEntityToPostInfo(Post post) {
    return PostInfo.builder()
        .title(post.getTitle())
        .creatorName(post.getCreator().getNickName())
        .chatUrl(post.getChatUrl())
        .startAt(post.getStartAt())
        .limitMember(post.getLimitMember())
        .foodCategory(post.getFoodCategory())
        .createdAt(post.getCreatedAt())
        .build();
  }
}
