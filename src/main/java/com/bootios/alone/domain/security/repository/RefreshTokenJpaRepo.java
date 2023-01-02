package com.bootios.alone.domain.security.repository;

import com.bootios.alone.domain.security.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenJpaRepo extends JpaRepository<RefreshToken, String> {

    Optional<RefreshToken> findByKey(Long key);
}
