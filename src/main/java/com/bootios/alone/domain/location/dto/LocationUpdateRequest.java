package com.bootios.alone.domain.location.dto;

import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

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
