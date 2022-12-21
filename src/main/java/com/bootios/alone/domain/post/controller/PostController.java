package com.bootios.alone.domain.post.controller;

import com.bootios.alone.domain.post.dto.PostCreateRequest;
import com.bootios.alone.domain.post.dto.PostInfo;
import com.bootios.alone.domain.post.dto.PostUpdateRequest;
import com.bootios.alone.domain.post.service.PostService;
import javax.validation.Valid;

import com.bootios.alone.global.result.ResultCode;
import com.bootios.alone.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
