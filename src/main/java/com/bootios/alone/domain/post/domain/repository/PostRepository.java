package com.bootios.alone.domain.post.domain.repository;

import com.bootios.alone.domain.post.domain.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
