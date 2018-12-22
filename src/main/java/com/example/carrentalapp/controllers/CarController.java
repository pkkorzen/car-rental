package com.example.carrentalapp.controllers;

import com.example.carrentalapp.dto.UserDto;
import com.example.carrentalapp.entities.*;
import com.example.carrentalapp.entities.enums.Gearbox;
import com.example.carrentalapp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class CarController {

    private CarService carService;
    private TypeService typeService;
    private UserService userService;
    private LocationService locationService;

    @Autowired
    public CarController(CarService carService, TypeService typeService, UserService userService,
                         LocationService locationService) {
        this.carService = carService;
        this.typeService = typeService;
        this.userService = userService;
        this.locationService = locationService;
    }

    @GetMapping("/all-cars")
    public String showAllCars(Model model, Authentication authentication) {
        List<Car> cars = carService.findAllCars();
        model.addAttribute("cars", cars);
        model.addAttribute("text", "All");
        setUserRoleAttribute(model, authentication);
        return "cars/fleet";
    }

    private void setUserRoleAttribute(Model model, Authentication authentication) {
        Optional<UserDto> userOptional = userService.findUserByLogin(authentication.getName());
        userOptional.ifPresent(user -> model.addAttribute("userRole", user.getRole()));
    }

    @PostMapping("/available-cars")
    public String showAvailableCars(Model model, @RequestParam(name = "startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                    @RequestParam(name = "endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                    @RequestParam(name = "startLocation") Long startLocationId,
                                    @RequestParam(name = "endLocation") Long endLocationId,
                                    Authentication authentication) {
        Location rentalLocation = findLocation(locationService, startLocationId);
        Location returnLocation = findLocation(locationService, endLocationId);
        model.addAttribute("startLocation", rentalLocation);
        model.addAttribute("endLocation", returnLocation);

        List<Car> cars = carService.findCarsAvailableByDatesAndLocation(startDate, endDate, rentalLocation, returnLocation);
        model.addAttribute("cars", cars);
        model.addAttribute("text", "Available");
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
        setUserRoleAttribute(model, authentication);
        return "cars/all-cars";
    }

    private Location findLocation(LocationService locationService, Long locationId) {
        Optional<Location> location = locationService.findLocationById(locationId);
        return location.orElseGet(()-> locationService.findLocationById(10L).get());
    }

    @PostMapping("cars/save")
    public String saveCar(@ModelAttribute Car car, @RequestParam("pressed-button") String pushedButton) {

        if (pushedButton.equalsIgnoreCase("save")) {
            carService.saveCar(car);
        }
        return "redirect:/all-cars";
    }

    @GetMapping("cars/add")
    public String addCar(Model model) {
        List<Type> types = typeService.findAll();
        model.addAttribute("text", "New");
        model.addAttribute("car", new Car());
        model.addAttribute("gearboxType", Gearbox.values());
        model.addAttribute("types", types);
        return "cars/car";
    }

    @GetMapping("cars/edit/{id}")
    public String editCar(@PathVariable Long id, Model model) {
        List<Type> types = typeService.findAll();
        model.addAttribute("text", "Edit");
        model.addAttribute("gearboxType", Gearbox.values());
        model.addAttribute("types", types);
        Optional<Car> carOptional = carService.findCarById(id);
        carOptional.ifPresent(car -> model.addAttribute("car", car));

        return "cars/car";
    }
}
