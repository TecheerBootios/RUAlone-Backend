package com.bootios.alone.domain.kakao.client;

import com.bootios.alone.domain.kakao.config.KakaoFeignConfiguration;
import com.bootios.alone.domain.kakao.dto.KakaoInfo;
import com.bootios.alone.domain.kakao.dto.KakaoToken;
import java.net.URI;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "kakaoClient", configuration = KakaoFeignConfiguration.class)
public interface KakaoClient {

  @PostMapping
  KakaoInfo getInfo(URI baseUrl, @RequestHeader("Authorization") String accessToken);

  @PostMapping
  KakaoToken getToken(
      URI baseUrl,
      @RequestParam("client_id") String restApiKey,
      @RequestParam("redirect_uri") String redirectUrl,
      @RequestParam("code") String code,
      @RequestParam("grant_type") String grantType);
}
