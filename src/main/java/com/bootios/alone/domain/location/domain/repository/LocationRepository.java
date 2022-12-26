package com.bootios.alone.domain.location.domain.repository;

import com.bootios.alone.domain.location.domain.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {}
