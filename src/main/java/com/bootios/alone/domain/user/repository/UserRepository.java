package com.bootios.alone.domain.user.repository;

import com.bootios.alone.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  @Query("select u from User u where u.userId = :id and u.isActive = true")
  Optional<User> findUserById(Long id);
  List<User> findByName(String name);

  Optional<User> findByEmail(String email);

  Optional<User> findByEmailAndProvider(String email, String provider);
}
