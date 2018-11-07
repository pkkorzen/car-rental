package com.example.carrentalapp.repositories;

import com.example.carrentalapp.entities.Rental;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface RentalRepository extends CrudRepository<Rental, Long> {
    Iterable<Rental> findAllByRentalDateBeforeAndPlannedDateAfter(LocalDate endDate, LocalDate startDate);
}
