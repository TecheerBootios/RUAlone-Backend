package com.bootios.alone.domain.user.repository;

import com.bootios.alone.domain.user.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findUserBykakaoName(String kakaoName);

  Optional<User> findUserById(Long id);

  boolean existsBykakaoEmail(String kakaoEmail);
}
