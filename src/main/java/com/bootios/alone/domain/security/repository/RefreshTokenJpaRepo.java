package com.bootios.alone.domain.security.repository;

import com.bootios.alone.domain.security.entity.RefreshToken;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RefreshTokenJpaRepo extends JpaRepository<RefreshToken, String> {

  Optional<RefreshToken> findByKey(Long key);
}
