package com.example.carrentalapp.services;

import com.example.carrentalapp.entities.Car;
import com.example.carrentalapp.entities.Location;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface CarService {
    Optional<Car> findCarById(Long id);
    List<Car> findAllCars();
    List<Car> findCarsAvailableByDatesAndLocation(LocalDate startDate, LocalDate endDate, Location startLocation,
                                                  Location endLocation);
    void saveCar(Car car);
    List<Car> findAllAvailableCars();
    void deleteCar(Long id);
}
