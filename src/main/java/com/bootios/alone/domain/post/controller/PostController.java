package com.bootios.alone.domain.post.controller;

import com.bootios.alone.domain.post.dto.PostCreateRequest;
import com.bootios.alone.domain.post.dto.PostInfo;
import com.bootios.alone.domain.post.service.PostService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class PostController {

  private final PostService postService;

  @PostMapping("/api/post")
  public ResponseEntity<PostInfo> createPost(
      @Valid @RequestBody PostCreateRequest postCreateRequest) {
    PostInfo postInfo = postService.createPost(postCreateRequest);
    return ResponseEntity.ok(postInfo);
  }
}
