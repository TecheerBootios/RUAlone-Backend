package com.bootios.alone.domain.user.service;

import com.bootios.alone.domain.user.dto.UserDto;
import com.bootios.alone.domain.user.entity.Authority;
import com.bootios.alone.domain.user.entity.User;
import com.bootios.alone.domain.user.exception.NotFoundMemberException;
import com.bootios.alone.domain.user.repository.UserRepository;
import com.bootios.alone.global.utils.SecurityUtil;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;
  private final PasswordEncoder passwordEncoder;

  public User findUserById(long id) {
    return userRepository.findUserById(id).orElseThrow(RuntimeException::new);
  }

  public User findUserByNickname(String nickname) {
    return userRepository.findUserByNickname(nickname).orElseThrow(RuntimeException::new);
  }

  @Transactional
  public User signup(UserDto userDto) {
    if (userRepository.findOneWithAuthoritiesByUsername(userDto.getUsername()).orElse(null)
        != null) {
      throw new RuntimeException("이미 가입되어 있는 유저입니다.");
    }

    Authority authority = Authority.builder().authorityName("ROLE_USER").build();

    User user =
        User.builder()
            .username(userDto.getUsername())
            .password(passwordEncoder.encode(userDto.getPassword()))
            .nickname(userDto.getNickname())
            .authorities(Collections.singleton(authority))
            .build();

    return userRepository.save(user);
  }

  /**
   * username 기준으로 user와 권한 정보를 가져온다.
   *
   * @param username
   * @return
   */
  @Transactional(readOnly = true)
  public UserDto getUserWithAuthorities(String username) {
    return UserDto.from(userRepository.findOneWithAuthoritiesByUsername(username).orElse(null));
  }

  /**
   * 현재 SecurityContext에 저장된 username을 가져온다.
   *
   * @return
   */
  @Transactional(readOnly = true)
  public UserDto getMyUserWithAuthorities() {
    return UserDto.from(
        SecurityUtil.getCurrentUsername()
            .flatMap(userRepository::findOneWithAuthoritiesByUsername)
            .orElseThrow(() -> new NotFoundMemberException("Member not found")));
  }
}
