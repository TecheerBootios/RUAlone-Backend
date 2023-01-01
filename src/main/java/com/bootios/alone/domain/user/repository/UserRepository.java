package com.bootios.alone.domain.user.repository;

import com.bootios.alone.domain.user.entity.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  Optional<User> findUserByNickname(String nickname);

  @Query("select u from User u where u.id = :id and u.isActive = true")
  Optional<User> findUserById(Long id);

  boolean existsByUsername(String username);

  /**
   * 카카오 이메일을 기준으로 User 정보를 가져올 때 권한도 같이 가져온다.
   *
   * @param username
   * @return
   */
  @EntityGraph(attributePaths = "authorities") // Eager 조회
  Optional<User> findOneWithAuthoritiesByUsername(String username);
}
