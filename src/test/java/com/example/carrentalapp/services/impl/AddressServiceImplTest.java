package com.example.carrentalapp.services.impl;

import com.example.carrentalapp.entities.Address;
import com.example.carrentalapp.services.AddressService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class AddressServiceImplTest {

    @Autowired
    private AddressService addressService;

    @Test
    public void shouldFindAddressById() {
        Address address = addressService.findAddressById(10L).get();
        assertEquals("Polna", address.getStreet());
    }
}