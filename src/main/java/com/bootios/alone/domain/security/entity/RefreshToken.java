package com.bootios.alone.domain.security.entity;

import com.bootios.alone.global.common.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "refresh_token")
@Getter
@NoArgsConstructor
public class RefreshToken extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // mysql에서 id auto increment 설정을 위해서는 int나 float으로 설정해야 해서 기존 string을 Long으로 수정함

    @Column(nullable = false, name = "token_key")   // mysql에 key가 예약어라서 column명을 바꿔줌
    private Long key;

    @Column(nullable = false)
    private String token;

    public RefreshToken updateToken(String token) {
        this.token = token;
        return this;
    }

    @Builder
    public RefreshToken(Long key, String token) {
        this.key = key;
        this.token = token;
    }
}
