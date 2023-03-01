package com.bootios.alone.domain.location.controller;

import com.bootios.alone.domain.location.dto.LocationCreateRequest;
import com.bootios.alone.domain.location.dto.LocationUpdateRequest;
import com.bootios.alone.domain.location.service.LocationService;
import com.bootios.alone.global.response.model.CommonResult;
import com.bootios.alone.global.response.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"5. Location"})
@RequiredArgsConstructor
@RestController
public class LocationController {

  private final LocationService locationService;
  private final ResponseService responseService;

  @ApiImplicitParams({
    @ApiImplicitParam(
        name = "X-AUTH-TOKEN",
        value = "로그인 성공 후 AccessToken",
        required = true,
        dataType = "String",
        paramType = "header")
  })
  @ApiOperation(value = "좌표 등록", notes = "좌표값을 등록합니다.")
  @PostMapping("/api/location")
  public ResponseEntity<CommonResult> createLocation(
      @Valid @RequestBody LocationCreateRequest locationCreateRequest) {

    locationService.registerLocation(locationCreateRequest);

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
  @ApiOperation(value = "좌표 변경", notes = "좌표값을 변경합니다.")
  @PutMapping("/api/location")
  public ResponseEntity<CommonResult> updateLocation(
      @Valid @RequestBody LocationUpdateRequest locationUpdateRequest) {

    locationService.updateLocation(locationUpdateRequest);

    return ResponseEntity.ok(responseService.getSuccessResult());
  }
}
