package com.example.carrentalapp.services.impl;

import com.example.carrentalapp.entities.Car;
import com.example.carrentalapp.entities.Location;
import com.example.carrentalapp.entities.Type;
import com.example.carrentalapp.entities.enums.Gearbox;
import com.example.carrentalapp.repositories.CarRepository;
import com.example.carrentalapp.services.CarService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CarServiceImplTest {

    @Autowired
    private CarService carService;

    @Test
    public void shouldFindCarById(){
        Optional<Car> carOptional = carService.findCarById(10L);
        assertEquals("Opel",carOptional.get().getMake());
    }

    @Test
    public void shouldFindAllCars(){
        List<Car> cars = carService.findAllCars();
        assertEquals(3, cars.size());
    }

    @Test
    public void shouldSaveCar(){
        List<Car> carsBeforeSave = carService.findAllCars();
        Car car = new Car();
        car.setMake("Subaru");
        car.setModel("Impreza");
        carService.saveCar(car);
        List<Car> carsAfterSave = carService.findAllCars();
        assertEquals(carsBeforeSave.size()+1, carsAfterSave.size());
    }

    @Test
    public void shouldFindCarsAvailableByDates() {
        LocalDate startDate = LocalDate.of(2018, Month.NOVEMBER, 1);
        LocalDate endDate = LocalDate.of(2018, Month.NOVEMBER,3);
        List<Car> cars = carService.findCarsAvailableByDates(startDate, endDate);
        assertEquals(2, cars.size());
    }
    @Test
    public void shouldFindCarsAvailableByDatesAndLocation() {
        LocalDate startDate = LocalDate.of(2018, Month.NOVEMBER, 21);
        LocalDate endDate = LocalDate.of(2018, Month.NOVEMBER,22);
        Location location = new Location();
        location.setId(10L);
        List<Car> cars = carService.findCarsAvailableByDatesAndLocation(startDate, endDate, location);
        assertEquals(1, cars.size());
    }

}