package com.example.carrentalapp.services.impl;

import com.example.carrentalapp.entities.Location;
import com.example.carrentalapp.repositories.LocationRepository;
import com.example.carrentalapp.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class LocationServiceImpl implements LocationService {

    private LocationRepository locationRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository){
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> findAllLocations() {
        Iterable<Location> locations = locationRepository.findAll();
        return StreamSupport.stream(locations.spliterator(), true).collect(Collectors.toList());
    }

    @Override
    public Optional<Location> findLocationById(Long id) {
        return locationRepository.findById(id);
    }

    @Override
    public void saveLocation(Location location) {
        locationRepository.save(location);
    }

    @Override
    public void deleteLocation(Long id) {
        Optional<Location> locationOptional = locationRepository.findById(id);
        locationOptional.ifPresent(location -> locationRepository.delete(location));
    }
}
