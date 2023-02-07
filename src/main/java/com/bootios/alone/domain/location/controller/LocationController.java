package com.bootios.alone.domain.location.controller;

import com.bootios.alone.domain.location.dto.LocationCreateRequest;
import com.bootios.alone.domain.location.service.LocationService;
import com.bootios.alone.global.response.model.CommonResult;
import com.bootios.alone.global.response.service.ResponseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"5. Location"})
@RequestMapping("/api/location")
@RequiredArgsConstructor
@RestController
public class LocationController {

  private final LocationService locationService;
  private final ResponseService responseService;

  @ApiOperation(value = "좌표 등록", notes = "좌표값을 등록합니다.")
  @PostMapping
  public ResponseEntity<CommonResult> createLocation(
      @Valid @RequestBody LocationCreateRequest locationCreateRequest) {

    locationService.registerLocation(locationCreateRequest);

    return ResponseEntity.ok(responseService.getSuccessResult());
  }

  @ApiOperation(value = "좌표 삭제", notes = "좌표값을 삭제합니다.")
  @PostMapping
  public ResponseEntity<CommonResult> deleteLocation(@Valid @RequestParam Long id) {

    locationService.deleteLocation(id);

    return ResponseEntity.ok(responseService.getSuccessResult());
  }
}
