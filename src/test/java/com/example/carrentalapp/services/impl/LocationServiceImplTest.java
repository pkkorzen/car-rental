package com.example.carrentalapp.services.impl;

import com.example.carrentalapp.entities.Location;
import com.example.carrentalapp.services.LocationService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class LocationServiceImplTest {
    @Autowired
    private LocationService locationService;

    @Test
    public void shouldFindAllLocations() {
        List<Location> locations = locationService.findAllLocations();
        assertEquals(3, locations.size());
    }

    @Test
    public void shouldFindLocationById() {
        Location location = locationService.findLocationById(10L).get();
        assertEquals("Jana Brzechwy", location.getStreet());
    }
}