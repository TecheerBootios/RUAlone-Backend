package com.bootios.alone.domain.location.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Builder
@Getter
@AllArgsConstructor
public class LocationUpdateRequest {

    @NotNull(message = "locationId는 공백이 올 수 없습니다.")
    private Long locationId;

    @NotNull(message = "latitude는 공백이 올 수 없습니다.")
    private Float latitude;
    @NotNull(message = "longitude는 공백이 올 수 없습니다.")
    private Float longitude;
}
