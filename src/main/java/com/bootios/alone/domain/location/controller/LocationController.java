package com.bootios.alone.domain.location.controller;

import com.bootios.alone.domain.location.dto.LocationCreateRequest;
import com.bootios.alone.domain.location.service.LocationService;
import com.bootios.alone.global.result.ResultCode;
import com.bootios.alone.global.result.ResultResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/api/location")
@RequiredArgsConstructor
@RestController
public class LocationController {

    private final LocationService locationService;

    @PostMapping
    public ResponseEntity<ResultResponse> createLocation(@Valid @RequestBody LocationCreateRequest locationCreateRequest) {

        locationService.registerLocation(locationCreateRequest);

        return ResponseEntity.ok(ResultResponse.of(ResultCode.CREATE_LOCATION_SUCCESS));
    }
}
