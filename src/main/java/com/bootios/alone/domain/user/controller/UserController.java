package com.bootios.alone.domain.user.controller;

import com.bootios.alone.domain.user.dto.request.UserRequestDto;
import com.bootios.alone.domain.user.dto.response.UserResponseDto;
import com.bootios.alone.domain.user.service.UserService;
import com.bootios.alone.global.response.model.CommonResult;
import com.bootios.alone.global.response.model.ListResult;
import com.bootios.alone.global.response.model.SingleResult;
import com.bootios.alone.global.response.service.ResponseService;
import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"3. User"})
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserController {

  private final UserService userService;
  private final ResponseService responseService;

  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "X-AUTH-TOKEN",
        value = "로그인 성공 후 AccessToken",
        required = true,
        dataType = "String",
        paramType = "header")
  })
  @ApiOperation(value = "회원 단건 검색", notes = "userId로 회원을 조회합니다.")
  @GetMapping("/user/id/{userId}")
  public SingleResult<UserResponseDto> findUserById(
      @ApiParam(value = "회원 ID", required = true) @PathVariable Long userId,
      @ApiParam(value = "언어", defaultValue = "ko") @RequestParam String lang) {
    return responseService.getSingleResult(userService.findById(userId));
  }

  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "X-AUTH-TOKEN",
        value = "로그인 성공 후 AccessToken",
        required = true,
        dataType = "String",
        paramType = "header")
  })
  @ApiOperation(value = "회원 단건 검색 (이메일)", notes = "이메일로 회원을 조회합니다.")
  @GetMapping("/user/email/{email}")
  public SingleResult<UserResponseDto> findUserByEmail(
      @ApiParam(value = "회원 이메일", required = true) @PathVariable String email,
      @ApiParam(value = "언어", defaultValue = "ko") @RequestParam String lang) {
    return responseService.getSingleResult(userService.findByEmail(email));
  }

  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "X-AUTH-TOKEN",
        value = "로그인 성공 후 AccessToken",
        required = true,
        dataType = "String",
        paramType = "header")
  })
  @ApiOperation(value = "회원 목록 조회", notes = "모든 회원을 조회합니다.")
  @GetMapping("/users")
  public ListResult<UserResponseDto> findAllUser() {
    return responseService.getListResult(userService.findAllUser());
  }

  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "X-AUTH-TOKEN",
        value = "로그인 성공 후 AccessToken",
        required = true,
        dataType = "String",
        paramType = "header")
  })
  @ApiOperation(value = "회원 수정", notes = "회원 정보를 수정합니다.")
  @PutMapping("/user")
  public SingleResult<Long> update(
      @ApiParam(value = "회원 ID", required = true) @RequestParam Long userId,
      @ApiParam(value = "회원 이름", required = true) @RequestParam String nickName) {
    UserRequestDto userRequestDto = UserRequestDto.builder().nickName(nickName).build();
    return responseService.getSingleResult(userService.update(userId, userRequestDto));
  }

  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "X-AUTH-TOKEN",
        value = "로그인 성공 후 AccessToken",
        required = true,
        dataType = "String",
        paramType = "header")
  })
  @ApiOperation(value = "회원 삭제", notes = "회원을 삭제합니다.")
  @DeleteMapping("/user/{userId}")
  public CommonResult delete(
      @ApiParam(value = "회원 아이디", required = true) @PathVariable Long userId) {
    userService.delete(userId);
    return responseService.getSuccessResult();
  }
}
