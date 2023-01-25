package com.bootios.alone.domain.location.repository;

import com.bootios.alone.domain.location.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    @Query("select l from Location l where l.post.id = :id and l.isActive = true")
    Optional<Location> findLocationByPostId(@Param("id") Long id);
}
