package com.example.carrentalapp.services.impl;

import com.example.carrentalapp.dto.UserDto;
import com.example.carrentalapp.entities.Address;
import com.example.carrentalapp.entities.Rental;
import com.example.carrentalapp.entities.User;
import com.example.carrentalapp.services.RentalService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RentalServiceImplTest {

    @Autowired
    private RentalService rentalService;

    @Test
    public void shouldFindRentalsBetweenGivenDates() {
        LocalDate startDate = LocalDate.of(2018, Month.NOVEMBER, 1);
        LocalDate endDate = LocalDate.of(2018, Month.NOVEMBER,3);
        Iterable<Rental> rentals = rentalService.findRentalsByDateBetween(startDate, endDate);
        assertEquals(1, ((List<Rental>) rentals).size());
    }

    @Test
    public void shouldFindRentalsForGivenUser(){
        UserDto user = new UserDto();
        user.setId(10L);

        Iterable<Rental> rentals = rentalService.findAllRentalsByUser(user);
        assertEquals(2, ((List<Rental>) rentals).size());
    }
}