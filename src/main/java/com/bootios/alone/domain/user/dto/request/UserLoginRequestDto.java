package com.bootios.alone.domain.user.dto.request;

import com.bootios.alone.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserLoginRequestDto {
  private String email;
  private String password;

  public User toUser(PasswordEncoder passwordEncoder) {
    return User.builder().email(email).password(passwordEncoder.encode(password)).build();
  }
}
