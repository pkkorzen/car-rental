package com.example.carrentalapp.repositories;

import com.example.carrentalapp.entities.Rental;
import com.example.carrentalapp.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;

public interface RentalRepository extends CrudRepository<Rental, Long> {
    Iterable<Rental> findAllByRentalDateBeforeAndReturnDateAfter(LocalDate endDate, LocalDate startDate);
    Iterable<Rental> findAllByUser (User user);
}
