package com.bootios.alone.domain.location.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class LocationInfo {
  private Float latitude;
  private Float longitude;
}
