package com.example.carrentalapp.repositories;

import com.example.carrentalapp.entities.Address;
import org.springframework.data.repository.CrudRepository;

public interface AddressRepository extends CrudRepository<Address, Long> {
}
