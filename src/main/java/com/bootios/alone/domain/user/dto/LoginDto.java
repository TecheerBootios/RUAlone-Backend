package com.bootios.alone.domain.user.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginDto {

  @NotNull
  @Size(min = 3, max = 50)
  private String username;

  @NotNull
  @Size(min = 3, max = 100)
  private String password;
}
