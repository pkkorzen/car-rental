package com.example.carrentalapp.services.impl;

import com.example.carrentalapp.entities.RentalStatus;
import com.example.carrentalapp.repositories.RentalStatusRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RentalStatusServiceImplTest {

    @Autowired
    private RentalStatusRepository rentalStatusRepository;

    @Test
    public void findRentalStatusById() {
        RentalStatus rentalStatus = rentalStatusRepository.findById(12L).get();
        assertEquals("rented", rentalStatus.getStatus());
    }
}