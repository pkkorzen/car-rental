package com.example.carrentalapp.repositories.impl;

import com.example.carrentalapp.entities.Car;
import com.example.carrentalapp.repositories.CustomCarRepository;
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
public class CustomCarRepositoryImplTest {

    @Autowired
    CustomCarRepository customCarRepository;
    @Test
    public void shouldFindCarsAvaialbleBetweenDates() {
        LocalDate startDate = LocalDate.of(2018, Month.NOVEMBER, 1);
        LocalDate endDate = LocalDate.of(2018, Month.NOVEMBER,3);
        List<Car> cars = customCarRepository.findCarsAvaialbleBetweenDates(startDate, endDate);
        assertEquals(2, cars.size());
    }
}