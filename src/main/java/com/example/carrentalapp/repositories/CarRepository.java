package com.example.carrentalapp.repositories;

import com.example.carrentalapp.entities.Car;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface CarRepository extends CrudRepository<Car, Long> {
    @Query(value = "SELECT c FROM Car c WHERE c.id NOT IN (SELECT ca.id FROM Car ca LEFT JOIN Rental r ON ca.id = r.car.id WHERE r.plannedDate > ?1 AND r.rentalDate < ?2)")
    List<Car> findCarsAvailableBetweenDates(LocalDate startDate, LocalDate endDate);
}
