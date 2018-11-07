package com.example.carrentalapp.services.impl;

import com.example.carrentalapp.entities.Car;
import com.example.carrentalapp.repositories.CarRepository;
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
public class CarServiceImplTest {

    @Autowired
    private CarRepository carRepository;

    @Test
    public void findCarsAvailableBetweenDates() {
        LocalDate startDate = LocalDate.of(2018, Month.NOVEMBER, 1);
        LocalDate endDate = LocalDate.of(2018, Month.NOVEMBER,3);
        List<Car> cars = carRepository.findCarsAvaialbleBetweenDates(startDate, endDate);
        assertEquals(2, cars.size());
    }
}