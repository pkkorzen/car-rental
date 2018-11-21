package com.example.carrentalapp.repositories;

import com.example.carrentalapp.entities.Location;
import org.springframework.data.repository.CrudRepository;

public interface LocationRepository extends CrudRepository<Location, Long> {
}
