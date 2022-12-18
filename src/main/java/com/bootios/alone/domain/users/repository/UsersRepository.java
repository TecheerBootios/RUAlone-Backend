package com.bootios.alone.domain.users.repository;

import com.bootios.alone.domain.users.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {

    @Query("select u from Users u where u.id =: id and u.isActive = true")
    Optional<Users> findUsersById(Long id);


}
