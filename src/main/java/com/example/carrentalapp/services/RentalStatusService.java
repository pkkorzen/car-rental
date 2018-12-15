package com.example.carrentalapp.services;

import com.example.carrentalapp.entities.RentalStatus;

import java.util.List;
import java.util.Optional;

public interface RentalStatusService {
    List<RentalStatus> findAll();
    Optional<RentalStatus> findRentalStatusById(Long id);
    Optional<RentalStatus> findRentalStatusByStatus(String status);
}
