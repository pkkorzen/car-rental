package com.example.carrentalapp.repositories;

import com.example.carrentalapp.entities.RentalStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RentalStatusRepository extends CrudRepository<RentalStatus, Long> {
    Optional<RentalStatus> findRentalStatusByStatus(String status);
}
