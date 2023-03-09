package com.bootios.alone.domain.user.dto.request;

import com.bootios.alone.domain.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestDto {

  private String email;
  private String name;
  private String nickName;

  public User toEntity() {
    return User.builder().email(email).name(name).nickName(nickName).build();
  }
}
