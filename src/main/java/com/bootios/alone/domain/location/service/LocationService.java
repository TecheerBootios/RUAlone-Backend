package com.bootios.alone.domain.location.service;

import com.bootios.alone.domain.location.dto.LocationCreateRequest;
import com.bootios.alone.domain.location.entity.Location;
import com.bootios.alone.domain.location.repository.LocationRepository;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LocationService {

  public final LocationRepository locationRepository;

  @Transactional
  public void registerLocation(LocationCreateRequest locationCreateRequest) {

    Location location = mapCreateRequestToEntity(locationCreateRequest);

    locationRepository.save(location);
  }

  public Location mapCreateRequestToEntity(LocationCreateRequest locationCreateRequest) {
    return Location.builder()
        .latitude(locationCreateRequest.getLatitude())
        .longitude(locationCreateRequest.getLongitude())
        .build();
  }

  public void deleteLocation(Long id) {
    Location foundLocation =
        locationRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    foundLocation.deleteLocation();
    locationRepository.save(foundLocation);
  }
}
