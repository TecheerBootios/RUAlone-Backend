package com.bootios.alone.domain.post.domain.repository;

import com.bootios.alone.domain.post.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("select p from Post p where p.id = :id and p.isActive = true")
    Optional<Post> findPostById(Long id);
}

