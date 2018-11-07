package com.example.carrentalapp.controllers;

import com.example.carrentalapp.entities.Car;
import com.example.carrentalapp.entities.Rental;
import com.example.carrentalapp.services.CarService;
import com.example.carrentalapp.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class CarController {

    private CarService carService;
    private RentalService rentalService;

    @Autowired
    public CarController(CarService carService, RentalService rentalService){
        this.carService = carService;
        this.rentalService = rentalService;
    }

    @GetMapping("/all-cars")
    public String showAllCars(Model model){
        List<Car> cars = carService.findAllCars();
        model.addAttribute("cars", cars);
        return "/all-cars";
    }

/*    @GetMapping("/available-cars")
    public String showAvailableCars(Model model, @RequestParam(name="startDate") LocalDate startDate,
                                    @RequestParam(name="endDate") LocalDate endDate){
        List<Car> cars = carService.findAllCars();
        List<Rental> rentals = rentalService.findRentalsByDateBetween(startDate, endDate);
        List<Car> rentedCars = rentals.stream().map(Rental::getCar).collect(Collectors.toList());
        cars.removeAll(rentedCars);
        model.addAttribute("cars", cars);
        return"/available-cars";
    }*/

//to wyglada na lepsze rozwiazanie z customRepository i jpql query aniżeli przerabianie danych z 2 tabel w javie
    @GetMapping("/available-cars")
    public String showAvailableCars(Model model, @RequestParam(name="startDate") LocalDate startDate,
                                    @RequestParam(name="endDate") LocalDate endDate){
        List<Car> cars = carService.findCarsAvailableBetweenDates(startDate, endDate);
        model.addAttribute("cars", cars);
        return"/available-cars";
    }

}
