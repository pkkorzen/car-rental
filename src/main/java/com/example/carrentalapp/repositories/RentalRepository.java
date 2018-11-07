package com.example.carrentalapp.repositories;

import com.example.carrentalapp.entities.Rental;
import org.springframework.data.repository.CrudRepository;

public interface RentalRepository extends CrudRepository<Rental, Long> {
}
