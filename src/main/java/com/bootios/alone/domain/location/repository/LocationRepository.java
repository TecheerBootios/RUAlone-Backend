package com.bootios.alone.domain.location.repository;

import com.bootios.alone.domain.location.entity.Location;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LocationRepository extends JpaRepository<Location, Long> {
  @Query("select l from Location l where l.post.id = :id and l.isActive = true")
  Optional<Location> findLocationByPostId(@Param("id") Long id);

  @Query(value = "select l.latitude, l.longitude " +
          "from Location l " +
//          "where ST_DWithin(CAST(ST_Point(:userLatitude, :userLongitude) AS geography), CAST(ST_Point(l.latitude, l.longitude) AS geography), 1500) and l.isActive is true",
          "where ST_DWithin(CAST(ST_SetSRID(ST_Point(:userLatitude, :userLongitude), 4326) AS geography), CAST(ST_SetSRID(ST_Point(l.latitude, l.longitude), 4326) AS geography), 1500) and l.is_active is true",
          nativeQuery = true)
  List<Location> findAll(@Param("userLatitude") float userLatitude, @Param("userLongitude") float userLongitude);
}
