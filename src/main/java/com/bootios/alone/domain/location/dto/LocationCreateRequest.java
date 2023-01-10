package com.bootios.alone.domain.location.dto;

import lombok.*;

import javax.validation.constraints.NotNull;

@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class LocationCreateRequest {

    @NotNull private Float xxCoordinate;
    @NotNull private Float yyCoordinate;
}
