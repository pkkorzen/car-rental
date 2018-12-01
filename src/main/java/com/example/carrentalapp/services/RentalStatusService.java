package com.example.carrentalapp.services;

import com.example.carrentalapp.entities.RentalStatus;

import java.util.Optional;

public interface RentalStatusService {
    Optional<RentalStatus> findRentalStatusById(Long id);
}
