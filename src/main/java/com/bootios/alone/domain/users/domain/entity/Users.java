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

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "address", nullable = false)
  private String address;

  @Column(name = "phone_number", nullable = false)
  private String phoneNumber;

  @Column(name = "role", nullable = false) // 관리자 or 사용자 구분
  @Enumerated(EnumType.STRING)
  private UserRole role;
}
