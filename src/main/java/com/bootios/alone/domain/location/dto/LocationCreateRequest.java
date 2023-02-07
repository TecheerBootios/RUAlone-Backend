package com.bootios.alone.domain.location.dto;

import javax.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class LocationCreateRequest {

  @NotNull(message = "latitude는 공백이 올 수 없습니다.")
  private Float latitude;

  @NotNull(message = "longitude는 공백이 올 수 없습니다.")
  private Float longitude;
}
