package com.bootios.alone.domain.post.controller;

import com.bootios.alone.domain.post.dto.PostCreateRequest;
import com.bootios.alone.domain.post.dto.PostInfo;
import com.bootios.alone.domain.post.dto.PostUpdateRequest;
import com.bootios.alone.domain.post.service.PostService;
import com.bootios.alone.global.response.model.CommonResult;
import com.bootios.alone.global.response.model.ListResult;
import com.bootios.alone.global.response.model.SingleResult;
import com.bootios.alone.global.response.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"4. Post"})
@RequiredArgsConstructor
@RestController
public class PostController {

  private final PostService postService;
  private final ResponseService responseService;

  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "X-AUTH-TOKEN",
        value = "로그인 성공 후 AccessToken",
        required = true,
        dataType = "String",
        paramType = "header")
  })
  @ApiOperation(value = "게시글 등록", notes = "게시글을 등록합니다.")
  @PostMapping("/api/post")
  public ResponseEntity<SingleResult> createPost(
      @Valid @RequestBody PostCreateRequest postCreateRequest) {
    PostInfo postInfo = postService.createPost(postCreateRequest);
    return ResponseEntity.ok(responseService.getSingleResult(postInfo));
  }

  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "X-AUTH-TOKEN",
        value = "로그인 성공 후 AccessToken",
        required = true,
        dataType = "String",
        paramType = "header")
  })
  @ApiOperation(value = "게시글 수정", notes = "게시글을 수정합니다.")
  @PutMapping("/api/post")
  public ResponseEntity<SingleResult> updatePost(
      @Valid @RequestBody PostUpdateRequest postUpdateRequest) {
    PostInfo postInfo = postService.updatePost(postUpdateRequest);
    return ResponseEntity.ok(responseService.getSingleResult(postInfo));
  }

  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "X-AUTH-TOKEN",
        value = "로그인 성공 후 AccessToken",
        required = true,
        dataType = "String",
        paramType = "header")
  })
  @ApiOperation(value = "게시글 삭제", notes = "게시글을 삭제합니다.")
  @DeleteMapping("/api/post/{id}")
  public ResponseEntity<CommonResult> deletePost(@PathVariable Long id) {
    postService.deletePost(id);
    return ResponseEntity.ok(responseService.getSuccessResult());
  }

  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "X-AUTH-TOKEN",
        value = "로그인 성공 후 AccessToken",
        required = true,
        dataType = "String",
        paramType = "header")
  })
  @ApiOperation(value = "게시글 단건 검색", notes = "게시글 번호로 게시글을 조회합니다.")
  @GetMapping("/api/post/{id}")
  public ResponseEntity<SingleResult> getPost(@PathVariable Long id) {
    PostInfo postDetail = postService.getPostDetail(id);
    return ResponseEntity.ok(responseService.getSingleResult(postDetail));
  }

  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "X-AUTH-TOKEN",
        value = "로그인 성공 후 AccessToken",
        required = true,
        dataType = "String",
        paramType = "header")
  })
  @ApiOperation(value = "게시글 목록", notes = "게시글 목록을 조회합니다.")
  @GetMapping("/api/post/list")
  public ResponseEntity<ListResult> getPostListByPagination(
      @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size) {
    List<PostInfo> postDetailList = postService.getPostListByPagination(page, size);
    return ResponseEntity.ok(responseService.getListResult(postDetailList));
  }

  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "X-AUTH-TOKEN",
        value = "로그인 성공 후 AccessToken",
        required = true,
        dataType = "String",
        paramType = "header")
  })
  @ApiOperation(value = "게시글 목록", notes = "게시글 목록을 조회합니다.")
  @GetMapping("/api/post/search")
  public ResponseEntity<ListResult> getPostListByPagination(
      @RequestParam(defaultValue = "0") int page,
      @RequestParam(defaultValue = "10") int size,
      @RequestParam(defaultValue = "") String keyword) {
    List<PostInfo> postDetailList =
        postService.searchPostListWithTitleByPagination(page, size, keyword);
    return ResponseEntity.ok(responseService.getListResult(postDetailList));
  }

  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "X-AUTH-TOKEN",
        value = "로그인 성공 후 AccessToken",
        required = true,
        dataType = "String",
        paramType = "header")
  })
  @ApiOperation(value = "반경 1.5km 내의 게시글 목록", notes = "반경 1.5km 내의 게시글 목록을 조회합니다.")
  @GetMapping("/api/post/list/distance")
  public ResponseEntity<ListResult> getPostListInDistance(
      @RequestParam Float userLatitude, @RequestParam Float userLongitude) {
    List<PostInfo> postInfoList = postService.getPostInDistance(userLatitude, userLongitude);
    return ResponseEntity.ok(responseService.getListResult(postInfoList));
  }
}
