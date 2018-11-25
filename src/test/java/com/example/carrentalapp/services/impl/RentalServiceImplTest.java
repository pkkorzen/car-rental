package com.example.carrentalapp.services.impl;

import com.example.carrentalapp.dto.UserDto;
import com.example.carrentalapp.entities.*;
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
    public void shouldFindRentalById(){
        Rental rental = rentalService.findRentalById(10L).get();
        assertEquals(LocalDate.of(2018, 11, 1),rental.getRentalDate());
    }

    @Test
    public void shouldFindAllRentals(){
        List<Rental> rentals = rentalService.findAllRentals();
        assertEquals(5, rentals.size());
    }

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
        user.setAddressId(13L);

        Iterable<Rental> rentals = rentalService.findAllRentalsByUser(user);
        assertEquals(4, ((List<Rental>) rentals).size());
    }

    @Test
    public void shouldSaveRental(){
        List<Rental> rentalsBeforeSave = rentalService.findAllRentals();
        Rental rental = new Rental();
        User user = new User();
        user.setId(10L);
        Car car = new Car();
        car.setId(10L);
        Location location = new Location();
        location.setId(10L);
        rental.setUser(user);
        rental.setCar(car);
        rental.setReturnPlace(location);
        rental.setRentalPlace(location);
        rental.setReturnDate(LocalDate.of(2018, 10, 10));
        rental.setRentalDate(LocalDate.of(2018, 10, 10));
        rentalService.saveRental(rental);
        List<Rental> rentalsAfterSave = rentalService.findAllRentals();
        assertEquals(rentalsBeforeSave.size()+1, rentalsAfterSave.size());
    }
}