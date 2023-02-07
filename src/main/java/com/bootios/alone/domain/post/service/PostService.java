package com.bootios.alone.domain.post.service;

import com.bootios.alone.domain.location.dto.LocationInfo;
import com.bootios.alone.domain.location.entity.Location;
import com.bootios.alone.domain.location.repository.LocationRepository;
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
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Slf4j
public class PostService {

  private final UserRepository userRepository;
  private final PostRepository postRepository;
  private final LocationRepository locationRepository;

  @Transactional
  public PostInfo createPost(PostCreateRequest postCreateRequest) {
    Long creatorId = postCreateRequest.getCreatorId();
    User foundCreator =
        userRepository.findUserById(creatorId).orElseThrow(CUserNotFoundException::new);

    Post newPost = mapCreateRequestToEntity(postCreateRequest, foundCreator);

    Post savedPost = postRepository.save(newPost);

    Location location = mapCreateRequestToLocation(postCreateRequest, savedPost);
    locationRepository.save(location);

    return mapPostEntityToPostInfo(savedPost);
  }

  private Location mapCreateRequestToLocation(PostCreateRequest postCreateRequest, Post savedPost) {
    return Location.builder()
        .latitude(postCreateRequest.getLatitude())
        .longitude(postCreateRequest.getLongitude())
        .post(savedPost)
        .build();
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

  @Transactional
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
    LocationInfo locationInfo = mapLocationInfoToPostEntity(post);

    return PostInfo.builder()
        .title(post.getTitle())
        .creatorName(post.getCreator().getNickName())
        .chatUrl(post.getChatUrl())
        .startAt(post.getStartAt())
        .limitMember(post.getLimitMember())
        .foodCategory(post.getFoodCategory())
        .locationInfo(locationInfo)
        .build();
  }

  private LocationInfo mapLocationInfoToPostEntity(Post post) {
    Location locationByPost =
        locationRepository
            .findLocationByPostId(post.getId())
            .orElseThrow(EntityNotFoundException::new);

    return LocationInfo.builder()
        .latitude(locationByPost.getLatitude())
        .longitude(locationByPost.getLongitude())
        .build();
  }

  private PostInfoList mapPostEntityToPostInfoList(Page<Post> postPage) {
    List<PostInfo> postInfos =
        postPage.stream().map(this::mapPostEntityToPostInfo).collect(Collectors.toList());
    return new PostInfoList(postInfos);
  }

  public PostInfoList getPostListByPagination(int page, int size) {
    PageRequest pageRequest = PageRequest.of(page, size);
    Page<Post> postListByPagination = postRepository.findPostWithPagination(pageRequest);
    return mapPostEntityToPostInfoList(postListByPagination);
  }

  public PostInfoList searchPostListWithTitleByPagination(int page, int size, String keyword) {
    PageRequest pageRequest = PageRequest.of(page, size);
    Page<Post> postListByPagination =
        postRepository.findContainingTitlePostWithPagination(pageRequest, keyword);

    return mapPostEntityToPostInfoList(postListByPagination);
  }
}
