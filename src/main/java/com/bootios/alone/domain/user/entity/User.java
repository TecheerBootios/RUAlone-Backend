package com.bootios.alone.domain.user.entity;

import com.bootios.alone.global.common.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Set;
import javax.persistence.*;
import lombok.*;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor // 인자 없는 생성자 생성
@AllArgsConstructor // 모든 인자 있는 생성자 생성
@Entity
@Setter
@Table(name = "User")
public class User extends BaseEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @Column(name = "user_id")
  private Long id;

  @JsonIgnore
  @Column(name = "password", length = 100)
  private String password; // 인증을 위한 password, 직접 사용하진 않는다.

  @Column(nullable = false, length = 30)
  private String username; // 카카오 이메일

  @Column(nullable = false, length = 100)
  private String nickname; // 카카오 닉네임

  @JsonIgnore
  @Column(name = "activated")
  private boolean activated;

  @ManyToMany
  @JoinTable(
      name = "user_authority",
      joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "user_id")},
      inverseJoinColumns = {
        @JoinColumn(name = "authority_name", referencedColumnName = "authority_name")
      })
  private Set<Authority> authorities;

  @Builder
  public User(String username, String password, String nickname, Set<Authority> authorities) {
    this.username = username;
    this.password = password;
    this.nickname = nickname;
    this.authorities = authorities;
  }
}
