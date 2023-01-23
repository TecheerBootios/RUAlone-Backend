package com.bootios.alone.domain.location.service;

import com.bootios.alone.domain.location.dto.LocationCreateRequest;
import com.bootios.alone.domain.location.entity.Location;
import com.bootios.alone.domain.location.repository.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LocationService {

  public final LocationRepository locationRepository;

  public void registerLocation(LocationCreateRequest locationCreateRequest) {

    Location location = createRequestToEntity(locationCreateRequest);

    locationRepository.save(location);
  }

  public Location createRequestToEntity(LocationCreateRequest locationCreateRequest) {
    return Location.builder()
        .xCoordinate(locationCreateRequest.getXxCoordinate())
        .yCoordinate(locationCreateRequest.getYyCoordinate())
        .build();
  }
}
