package com.bootios.alone.domain.post.controller;

import com.bootios.alone.domain.post.dto.PostCreateRequest;
import com.bootios.alone.domain.post.dto.PostInfo;
import com.bootios.alone.domain.post.dto.PostInfoList;
import com.bootios.alone.domain.post.dto.PostUpdateRequest;
import com.bootios.alone.domain.post.service.PostService;
import com.bootios.alone.global.result.ResultCode;
import com.bootios.alone.global.result.ResultResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"4. Post"})
@RequiredArgsConstructor
@RestController
public class PostController {

  private final PostService postService;

  @ApiOperation(value = "게시글 등록", notes = "게시글을 등록합니다.")
  @PostMapping("/api/post")
  public ResponseEntity<ResultResponse> createPost(
      @Valid @RequestBody PostCreateRequest postCreateRequest) {
    PostInfo postInfo = postService.createPost(postCreateRequest);
    return ResponseEntity.ok(ResultResponse.of(ResultCode.CREATE_POST_SUCCESS, postInfo));
  }

  @ApiOperation(value = "게시글 수정", notes = "게시글을 수정합니다.")
  @PutMapping("/api/post")
  public ResponseEntity<ResultResponse> updatePost(
      @Valid @RequestBody PostUpdateRequest postUpdateRequest) {
    PostInfo postInfo = postService.updatePost(postUpdateRequest);
    return ResponseEntity.ok(ResultResponse.of(ResultCode.UPDATE_POST_SUCCESS, postInfo));
  }

  @ApiOperation(value = "게시글 삭제", notes = "게시글을 삭제합니다.")
  @DeleteMapping("/api/post/{id}")
  public ResponseEntity<ResultResponse> deletePost(@PathVariable Long id) {
    postService.deletePost(id);
    return ResponseEntity.ok(ResultResponse.of(ResultCode.DELETE_POST_SUCCESS));
  }

  @ApiOperation(value = "게시글 단건 검색", notes = "게시글 번호로 게시글을 조회합니다.")
  @GetMapping("/api/post/{id}")
  public ResponseEntity<ResultResponse> getPost(@PathVariable Long id) {
    PostInfo postDetail = postService.getPostDetail(id);
    return ResponseEntity.ok(ResultResponse.of(ResultCode.GET_ONE_POST_SUCCESS, postDetail));
  }

  @ApiOperation(value = "게시글 목록", notes = "게시글 목록을 조회합니다.")
  @GetMapping("/api/post/list")
  public ResponseEntity<ResultResponse> getPostListByPagination(
      @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
    PostInfoList postDetailList = postService.getPostListByPagination(page, size);
    return ResponseEntity.ok(
        ResultResponse.of(ResultCode.GET_POST_PAGINATION_SUCCESS, postDetailList));
  }

  @ApiOperation(value = "게시글 목록", notes = "게시글 목록을 조회합니다.")
  @GetMapping("/api/post/search")
  public ResponseEntity<ResultResponse> getPostListByPagination(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(defaultValue = "") String keyword) {
    PostInfoList postDetailList =
        postService.searchPostListWithTitleByPagination(page, size, keyword);
    return ResponseEntity.ok(
        ResultResponse.of(ResultCode.SEARCH_POST_BY_TITLE_PAGINATION_SUCCESS, postDetailList));
  }
}
