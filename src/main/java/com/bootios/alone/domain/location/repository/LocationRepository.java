package com.bootios.alone.domain.location.repository;

import com.bootios.alone.domain.location.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocationRepository extends JpaRepository<Location, Long> {
    Optional<Location> findLocationByPostId(Long id);
}
