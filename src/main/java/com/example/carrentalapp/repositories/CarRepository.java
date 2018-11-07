package com.example.carrentalapp.repositories;

import com.example.carrentalapp.entities.Car;
import org.springframework.data.repository.CrudRepository;

public interface CarRepository extends CrudRepository<Car, Long> {
}
