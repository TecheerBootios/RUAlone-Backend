package com.bootios.alone.domain.users.domain.entity;

import com.bootios.alone.global.common.BaseEntity;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "users")
public class Users extends BaseEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "kakao_name", nullable = false)
  private String kakaoName;

  @Column(name = "kakao_email", nullable = false)
  private String kakaoEmail;
}
