package com.bootios.alone.domain.post.controller;

import com.bootios.alone.domain.post.dto.PostCreateRequest;
import com.bootios.alone.domain.post.dto.PostInfo;
import com.bootios.alone.domain.post.dto.PostInfoList;
import com.bootios.alone.domain.post.dto.PostUpdateRequest;
import com.bootios.alone.domain.post.service.PostService;
import com.bootios.alone.global.result.ResultCode;
import com.bootios.alone.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
public class PostController {

  private final PostService postService;

  @PostMapping("/api/post")
  public ResponseEntity<ResultResponse> createPost(
      @Valid @RequestBody PostCreateRequest postCreateRequest) {
    PostInfo postInfo = postService.createPost(postCreateRequest);
    return ResponseEntity.ok(ResultResponse.of(ResultCode.CREATE_POST_SUCCESS, postInfo));
  }

  @PutMapping("/api/post")
  public ResponseEntity<ResultResponse> updatePost(
      @Valid @RequestBody PostUpdateRequest postUpdateRequest) {
    PostInfo postInfo = postService.updatePost(postUpdateRequest);
    return ResponseEntity.ok(ResultResponse.of(ResultCode.UPDATE_POST_SUCCESS, postInfo));
  }

  @DeleteMapping("/api/post/{id}")
  public ResponseEntity<ResultResponse> deletePost(@PathVariable Long id) {
    postService.deletePost(id);
    return ResponseEntity.ok(ResultResponse.of(ResultCode.DELETE_POST_SUCCESS));
  }

  @GetMapping("/api/post/{id}")
  public ResponseEntity<ResultResponse> getPost(@PathVariable Long id) {
    PostInfo postDetail = postService.getPostDetail(id);
    return ResponseEntity.ok(ResultResponse.of(ResultCode.GET_ONE_POST_SUCCESS, postDetail));
  }

  @GetMapping("/api/post/list")
  public ResponseEntity<ResultResponse> getPostListByPagination(
      @RequestParam(defaultValue = "0") int page, @RequestParam("10") int size) {
    PostInfoList postDetailList = postService.getPostListByPagination(page, size);
    return ResponseEntity.ok(
        ResultResponse.of(ResultCode.GET_POST_PAGINATION_SUCCESS, postDetailList));
  }
}
