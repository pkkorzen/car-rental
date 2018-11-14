package com.example.carrentalapp.services;

import com.example.carrentalapp.entities.Address;

import java.util.Optional;

public interface AddressService {
    Optional<Address> findAddressById(Long id);
}
