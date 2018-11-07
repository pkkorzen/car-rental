package com.example.carrentalapp.services;

import com.example.carrentalapp.entities.Rental;

import java.util.List;
import java.util.Optional;

public interface RentalService {
    Optional<Rental> findRentalById(Long id);
    List<Rental> findAllRentals();
    void saveRental(Rental rental);
    void deleteRental(Long id);
}
