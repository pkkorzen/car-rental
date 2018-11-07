package com.example.carrentalapp.controllers;

import com.example.carrentalapp.entities.Car;
import com.example.carrentalapp.entities.Rental;
import com.example.carrentalapp.services.CarService;
import com.example.carrentalapp.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
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
        return "cars/all-cars";
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
        return"cars/available-cars";
    }

    @PostMapping("cars/save")
    public String saveCar(@ModelAttribute Car car, @RequestParam("pressed-button") String pushedButton){

        if(pushedButton.equalsIgnoreCase("save")){
            carService.saveCar(car);
        }
        return "redirect:/all-cars";
    }

    @GetMapping("cars/add/")
    public String addCar(Model model){
        model.addAttribute("text", "New");
        model.addAttribute("car", new Car());
        return "cars/car";
    }

    @GetMapping("cars/edit/{id}")
    public String editCar(@PathVariable Long id, Model model){
        model.addAttribute("text", "Edit");

        Optional<Car> carOptional = carService.findCarById(id);
        carOptional.ifPresent(car -> model.addAttribute("car", car));

        return "cars/car";
    }

    @GetMapping("cars/delete-confirmation/{id}")
    public String deleteConfirmation(@PathVariable Long id, Model model){
        Optional<Car> carOptional = carService.findCarById(id);
        carOptional.ifPresent(car -> model.addAttribute("carToAsk", car));
        return "cars/delete-confirmation";
    }

    @GetMapping("cars/delete/{id}")
    public String deleteCar(@PathVariable Long id){

        carService.deleteCar(id);

        return "cars/all-cars";
    }
}
