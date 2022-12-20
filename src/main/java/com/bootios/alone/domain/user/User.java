package com.bootios.alone.domain.user;

import com.bootios.alone.global.common.BaseEntity;
import lombok.AccessLevel;
import lombok.*;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@Getter
@NoArgsConstructor // 인자 없는 생성자 생성
@AllArgsConstructor // 모든 인자 있는 생성자 생성
@Entity
@Table(name = "User")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false, length = 30)
    private String kakaoName;

    @Column(nullable = false, length = 100)
    private String kakaoEmail;

    @Builder
    public User(String kakaoName, String kakaoEmail) {
        this.kakaoName = kakaoName;
        this.kakaoEmail = kakaoEmail;
    }
}
