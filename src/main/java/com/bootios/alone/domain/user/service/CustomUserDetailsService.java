package com.bootios.alone.domain.user.service;

import com.bootios.alone.domain.user.repository.UserRepository;
import com.bootios.alone.domain.user.exception.CUserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class CustomUserDetailsService implements UserDetailsService {

  private final UserRepository userRepository;

  @Override
  @Transactional(readOnly = true)
  public UserDetails loadUserByUsername(String userPk) throws UsernameNotFoundException {
    return userRepository.findById(Long.parseLong(userPk)).orElseThrow(CUserNotFoundException::new);
  }
}
