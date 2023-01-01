package com.bootios.alone.domain.user.controller;

import com.bootios.alone.domain.user.dto.LoginDto;
import com.bootios.alone.domain.user.dto.TokenDto;
import com.bootios.alone.global.utils.jwt.JwtFilter;
import com.bootios.alone.global.utils.jwt.TokenProvider;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@Slf4j
public class AuthController {
  private final TokenProvider tokenProvider;
  private final AuthenticationManagerBuilder authenticationManagerBuilder;

  public AuthController(
      TokenProvider tokenProvider, AuthenticationManagerBuilder authenticationManagerBuilder) {
    this.tokenProvider = tokenProvider;
    this.authenticationManagerBuilder = authenticationManagerBuilder;
  }

  @PostMapping("/authenticate")
  public ResponseEntity<TokenDto> authorize(@Valid @RequestBody LoginDto loginDto) {

    UsernamePasswordAuthenticationToken authenticationToken =
        new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword());
    log.info(String.valueOf(authenticationToken) + "whatthefuck");

    Authentication authentication =
        authenticationManagerBuilder.getObject().authenticate(authenticationToken);
    SecurityContextHolder.getContext().setAuthentication(authentication);

    String jwt = tokenProvider.createToken(authentication);

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add(JwtFilter.AUTHORIZATION_HEADER, "Bearer " + jwt);

    return new ResponseEntity<>(new TokenDto(jwt), httpHeaders, HttpStatus.OK);
  }
}
