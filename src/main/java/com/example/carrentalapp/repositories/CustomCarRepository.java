package com.example.carrentalapp.repositories;

import com.example.carrentalapp.entities.Car;

import java.time.LocalDate;
import java.util.List;

public interface CustomCarRepository {
    List<Car> findCarsAvaialbleBetweenDates(LocalDate startDate, LocalDate endDate);
}
