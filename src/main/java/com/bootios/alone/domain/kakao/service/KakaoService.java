package com.bootios.alone.domain.kakao.service;

import com.bootios.alone.domain.kakao.client.KakaoClient;
import com.bootios.alone.domain.kakao.dto.KakaoAccount;
import com.bootios.alone.domain.kakao.dto.KakaoInfo;
import com.bootios.alone.domain.kakao.dto.KakaoToken;
import com.bootios.alone.domain.user.User;
import com.bootios.alone.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;


@Slf4j
@Service
@RequiredArgsConstructor
public class KakaoService {
    private final UserRepository userRepository;
    private final KakaoClient client;

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
            KakaoAccount kakaoInfo = client.getInfo(new URI(kakaoUserApiUrl), token.getTokenType() + " " + token.getAccessToken()).getKakaoAccount();
            // 토큰을 통해 인증된 상태
            // 사용자 저장
            // DB에 사용자 있으면 리턴 없으면 저장
            if(!userRepository.existsBykakaoEmail(kakaoInfo.getEmail())){
                log.info("사용자 저장");

                userRepository.save(
                        User.builder()
                                .kakaoName(kakaoInfo.getProfile().getNickname())
                                .kakaoEmail(kakaoInfo.getEmail())
                                .build());

                return client.getInfo(new URI(kakaoUserApiUrl), token.getTokenType() + " " + token.getAccessToken());
            }

            log.info("사용자 이미 존재");
            return client.getInfo(new URI(kakaoUserApiUrl), token.getTokenType() + " " + token.getAccessToken());
        } catch (Exception e) {
            log.error("[log] can't get kakao information.", e);
            return KakaoInfo.fail();
        }
    }

    private KakaoToken getToken(final String code) {
        try {
            return client.getToken(new URI(kakaoAuthUrl), restapiKey, redirectUrl, code, "authorization_code");
        } catch (Exception e) {
            log.error("[log] can't get kakao token.", e);
            return KakaoToken.fail();
        }
    }
}
