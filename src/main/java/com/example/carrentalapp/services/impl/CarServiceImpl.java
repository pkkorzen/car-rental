package com.example.carrentalapp.services.impl;

import com.example.carrentalapp.entities.Car;
import com.example.carrentalapp.entities.Location;
import com.example.carrentalapp.repositories.CarRepository;
import com.example.carrentalapp.services.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CarServiceImpl implements CarService {

    private CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    @Override
    public Optional<Car> findCarById(Long id) {
        return carRepository.findById(id);
    }

    @Override
    public List<Car> findAllCars() {
        Iterable<Car> carIterable = carRepository.findAll();
        return StreamSupport.stream(carIterable.spliterator(), true).collect(Collectors.toList());
    }

    @Override
    public List<Car> findCarsAvailableByDates(LocalDate startDate, LocalDate endDate) {
        return carRepository.findCarsAvailableBetweenDates(startDate, endDate);
    }

    @Override
    public List<Car> findCarsAvailableByDatesAndLocation(LocalDate startDate, LocalDate endDate, Location location) {
        return carRepository.findCarsAvailableBetweenDatesInGivenLocation(startDate, endDate, location);
    }

    @Override
    public List<Car> findCarsAvailableByDatesAndLocation(LocalDate startDate, LocalDate endDate, Location startLocation, Location endLocation) {
        //date is being added here since JPQL Query did not recognize function dateadd for H2
        LocalDate nextDate = endDate.plusDays(1);
        return carRepository.findCarsAvailableByDatesAndLocation(startDate, endDate, nextDate, startLocation, endLocation);
    }

    @Override
    public void saveCar(Car car) {
        carRepository.save(car);
    }

    @Override
    public void deleteCar(Long id) {
        Optional<Car> result = carRepository.findById(id);
        result.ifPresent(car -> carRepository.delete(car));
    }
}
