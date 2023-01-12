package com.bootios.alone.domain.location.dto;

import javax.validation.constraints.NotNull;
import lombok.*;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class LocationCreateRequest {

  @NotNull private Float xxCoordinate;
  @NotNull private Float yyCoordinate;
}
