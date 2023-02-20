package com.bootios.alone.domain.location.service;

import com.bootios.alone.domain.location.dto.LocationCreateRequest;
import com.bootios.alone.domain.location.dto.LocationInfo;
import com.bootios.alone.domain.location.dto.LocationUpdateRequest;
import com.bootios.alone.domain.location.entity.Location;
import com.bootios.alone.domain.location.repository.LocationRepository;
import javax.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class LocationService {

  public final LocationRepository locationRepository;

  @Transactional
  public void registerLocation(LocationCreateRequest locationCreateRequest) {

    Location location = mapCreateRequestToEntity(locationCreateRequest);

    locationRepository.save(location);
  }

  @Transactional
  public List<LocationInfo> getLocationInDistance(Float userLatitude, Float userLongitude) {
    return locationRepository.findAll(userLatitude, userLongitude)
            .stream()
            .map(this::mapLocationEntityToLocationInfo)
            .collect(Collectors.toList());
  }

  @Transactional
  public LocationInfo updateLocation(LocationUpdateRequest locationUpdateRequest) {
    Location foundLocation =
        locationRepository
            .findById(locationUpdateRequest.getLocationId())
            .orElseThrow(EntityNotFoundException::new);

    foundLocation.update(locationUpdateRequest);

    Location savedLocation = locationRepository.save(foundLocation);
    return mapLocationEntityToLocationInfo(savedLocation);
  }

  public LocationInfo mapLocationEntityToLocationInfo(Location location) {
    Location foundLocation =
        locationRepository.findById(location.getId()).orElseThrow(EntityNotFoundException::new);

    return LocationInfo.builder()
        .latitude(foundLocation.getLatitude())
        .longitude(foundLocation.getLongitude())
        .build();
  }

  public Location mapCreateRequestToEntity(LocationCreateRequest locationCreateRequest) {
    return Location.builder()
        .latitude(locationCreateRequest.getLatitude())
        .longitude(locationCreateRequest.getLongitude())
        .build();
  }
}
