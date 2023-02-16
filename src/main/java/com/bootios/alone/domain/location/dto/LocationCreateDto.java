package com.bootios.alone.domain.location.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class LocationCreateDto {
  @NotNull(message = "latitude는 공백이 올 수 없습니다.")
  @Min(value = 0, message = "위도는 0이하가 될 수 없습니다.")
  private Float latitude;

  @NotNull(message = "longitude는 공백이 올 수 없습니다.")
  @Min(value = 0, message = "경도는 0이하가 될 수 없습니다.")
  private Float longitude;
}
