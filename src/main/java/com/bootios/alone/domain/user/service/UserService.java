package com.bootios.alone.domain.user.service;

import com.bootios.alone.domain.user.User;
import com.bootios.alone.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
  private final UserRepository userRepository;

  public User findUserById(long id) {
    return userRepository.findUserById(id).orElseThrow(RuntimeException::new);
  }

  public User findUserBykakaoName(String kakaoName) {
    return userRepository.findUserBykakaoName(kakaoName).orElseThrow(RuntimeException::new);
  }
}
