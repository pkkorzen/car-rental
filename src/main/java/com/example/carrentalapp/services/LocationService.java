package com.example.carrentalapp.services;

import com.example.carrentalapp.entities.Location;

import java.util.List;
import java.util.Optional;

public interface LocationService {
    List<Location> findAllLocations();
    Optional<Location> findLocationById(Long id);
    void saveLocation(Location location);
    void deleteLocation(Long id);
}
