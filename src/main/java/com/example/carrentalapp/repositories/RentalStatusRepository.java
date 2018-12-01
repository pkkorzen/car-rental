package com.example.carrentalapp.repositories;

import com.example.carrentalapp.entities.RentalStatus;
import org.springframework.data.repository.CrudRepository;

public interface RentalStatusRepository extends CrudRepository<RentalStatus, Long> {
}
