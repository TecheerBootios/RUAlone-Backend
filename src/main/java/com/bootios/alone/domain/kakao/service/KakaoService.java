package com.bootios.alone.domain.kakao.service;

import com.bootios.alone.domain.kakao.client.KakaoClient;
import com.bootios.alone.domain.kakao.dto.KakaoAccount;
import com.bootios.alone.domain.kakao.dto.KakaoInfo;
import com.bootios.alone.domain.kakao.dto.KakaoToken;
import com.bootios.alone.domain.user.dto.AuthorityDto;
import com.bootios.alone.domain.user.dto.UserDto;
import com.bootios.alone.domain.user.entity.Authority;
import com.bootios.alone.domain.user.entity.User;
import com.bootios.alone.domain.user.repository.UserRepository;
import java.net.URI;
import java.util.Collections;
import java.util.Set;

import com.bootios.alone.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoService {
  private final UserRepository userRepository;
  private final KakaoClient client;

  private final PasswordEncoder passwordEncoder;

  private final UserService userService;

  @Value("${kakao.auth-url}")
  private String kakaoAuthUrl;

  @Value("${kakao.user-api-url}")
  private String kakaoUserApiUrl;

  @Value("${kakao.restapi-key}")
  private String restapiKey;

  @Value("${kakao.redirect-url}")
  private String redirectUrl;

  public KakaoInfo getInfo(final String code) {
    final KakaoToken token = getToken(code);
    log.debug("token = {}", token);
    try {
      KakaoAccount kakaoInfo =
          client
              .getInfo(
                  new URI(kakaoUserApiUrl), token.getTokenType() + " " + token.getAccessToken())
              .getKakaoAccount();
      // 토큰을 통해 인증된 상태
      // 사용자 저장
      // DB에 사용자 있으면 리턴 없으면 저장
      if (!userRepository.existsByUsername(kakaoInfo.getEmail())) {
        log.info("사용자 저장");

        Authority authority = Authority.builder()
                .authorityName("ROLE_USER")
                .build();

        userService.signup(UserDto.builder()
                .username(kakaoInfo.getEmail())
                .password("$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi") // admin
                .nickname(kakaoInfo.getProfile().getNickname())
                .build()
        );

        return client.getInfo(
            new URI(kakaoUserApiUrl), token.getTokenType() + " " + token.getAccessToken());
      }

      log.info("사용자 이미 존재");
      return client.getInfo(
          new URI(kakaoUserApiUrl), token.getTokenType() + " " + token.getAccessToken());
    } catch (Exception e) {
      log.error("[log] can't get kakao information.", e);
      return KakaoInfo.fail();
    }
  }

  private KakaoToken getToken(final String code) {
    try {
      return client.getToken(
          new URI(kakaoAuthUrl), restapiKey, redirectUrl, code, "authorization_code");
    } catch (Exception e) {
      log.error("[log] can't get kakao token.", e);
      return KakaoToken.fail();
    }
  }
}
