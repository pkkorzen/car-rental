package com.example.carrentalapp.services.impl;

import com.example.carrentalapp.entities.Address;
import com.example.carrentalapp.repositories.AddressRepository;
import com.example.carrentalapp.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class AddressServiceImpl implements AddressService {

    private AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository){
        this.addressRepository = addressRepository;
    }
    @Override
    public Optional<Address> findAddressById(Long id) {
        return addressRepository.findById(id);
    }
}
