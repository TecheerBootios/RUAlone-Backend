package com.bootios.alone.domain.security.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TokenDto {
  private String grantType;
  private String accessToken;
  private String refreshToken;
  private Long accessTokenExpireDate;
  private String userEmail; // 클라이언트 요청으로 추가
}
