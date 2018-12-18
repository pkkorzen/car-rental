package com.example.carrentalapp.controllers;

import com.example.carrentalapp.converters.UserConverter;
import com.example.carrentalapp.dto.UserDto;
import com.example.carrentalapp.entities.*;
import com.example.carrentalapp.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Controller
public class RentalController {

    private RentalService rentalService;
    private UserService userService;
    private CarService carService;
    private LocationService locationService;
    private RentalStatusService rentalStatusService;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    public RentalController(RentalService rentalService, UserService userService, CarService carService, LocationService locationService, RentalStatusService rentalStatusService) {
        this.rentalService = rentalService;
        this.userService = userService;
        this.carService = carService;
        this.locationService = locationService;
        this.rentalStatusService = rentalStatusService;
    }

    @GetMapping("/all-rentals")
    public String showAllRentals(Model model, Authentication authentication) {
        String login = authentication.getName();
        Optional<UserDto> userOptional = userService.findUserByLogin(login);
        UserDto userDto = new UserDto();

        if (userOptional.isPresent()) {
            userDto = userOptional.get();
            model.addAttribute("userRole", userDto.getRole());
        }

        List<Rental> rentals;

        if ("ROLE_ADMIN".equals(userDto.getRole())) {
            rentals = rentalService.findAllRentals();
            model.addAttribute("text", "All");
        } else {
            rentals = rentalService.findAllRentalsByUser(userDto);
            model.addAttribute("text", "Your");
        }

        model.addAttribute("rentals", rentals);
        return "rentals/all-rentals";
    }

    @PostMapping("rentals/save")
    public String saveRental(@ModelAttribute Rental rental, @RequestParam("pressed-button") String pushedButton) {

        if (pushedButton.equalsIgnoreCase("save")) {
            rentalService.saveRental(rental);
        }

        return "redirect:/all-rentals";
    }

    @GetMapping("rentals/edit/{id}")
    public String editRental(@PathVariable Long id, Model model, Authentication authentication) {
        model.addAttribute("text", "Edit");

        List<UserDto> users = userService.findAll();

        Optional<Rental> rentalOptional = rentalService.findRentalById(id);

        LocalDate rentalDate = LocalDate.now();
        LocalDate returnDate = rentalDate.plus(1, ChronoUnit.DAYS);
        Location startLocation = null;
        Location endLocation = null;

        if (rentalOptional.isPresent()) {
            Rental rental = rentalOptional.get();
            model.addAttribute("rental", rental);
            rentalDate = rental.getRentalDate();
            returnDate = rental.getReturnDate();
            startLocation = rental.getRentalPlace();
            endLocation = rental.getReturnPlace();
        }

        List<Car> cars = carService.findCarsAvailableByDatesAndLocation(rentalDate, returnDate, startLocation, endLocation);
        Optional<UserDto> userOptional = userService.findUserByLogin(authentication.getName());
        userOptional.ifPresent(user -> model.addAttribute("userRole", user.getRole()));
        List<Location> locations = locationService.findAllLocations();
        List<RentalStatus> rentalStatuses = rentalStatusService.findAll();
        model.addAttribute("locations", locations);
        model.addAttribute("statuses", rentalStatuses);
        model.addAttribute("users", users);
        model.addAttribute("cars", cars);
        return "rentals/rental";
    }

    @GetMapping("rentals/rental-confirmation/{id}/{startDate}/{endDate}/{startLocationId}/{endLocationId}")
    public String confirmRental(@PathVariable Long id, Model model, Authentication authentication,
                                @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                @PathVariable Long startLocationId, @PathVariable Long endLocationId) {

        Rental rental = new Rental();

        String login = authentication.getName();
        Optional<UserDto> userOptional = userService.findUserByLogin(login);

        userOptional.ifPresent(userDto -> rental.setUser(userConverter.apply(userDto)));

        Optional<Location> rentalLocationOptional = locationService.findLocationById(startLocationId);
        rentalLocationOptional.ifPresent(rental::setRentalPlace);
        Optional<Location> returnLocationOptional = locationService.findLocationById(endLocationId);
        returnLocationOptional.ifPresent(rental::setReturnPlace);
        rental.setRentalDate(startDate);
        rental.setReturnDate(endDate);
        Optional<RentalStatus> rentalStatusOptional = rentalStatusService.findRentalStatusByStatus("reserved");
        rentalStatusOptional.ifPresent(rental::setRentalStatus);

        Optional<Car> carOptional = carService.findCarById(id);
        Type typeRented = new Type();
        if(carOptional.isPresent()){
            Car car = carOptional.get();
            typeRented = car.getType();
            rental.setCar(car);
        }

        int daysRented = Period.between(startDate, endDate).getDays();
        BigDecimal days = BigDecimal.valueOf(daysRented);

        BigDecimal totalPrice = typeRented.getPrice().multiply(days);
        model.addAttribute("price", totalPrice);

        model.addAttribute("rental", rental);
        return "rentals/rental-confirmation";
    }

    @GetMapping("rentals/cancel-confirmation/{id}")
    public String cancelConfirmation(@PathVariable Long id, Model model) {
        Optional<Rental> rentalOptional = rentalService.findRentalById(id);
        rentalOptional.ifPresent(rental -> model.addAttribute("rentalToAsk", rental));
        return "rentals/cancel-confirmation";
    }

    @GetMapping("rentals/cancel/{id}")
    public String cancelRental(@PathVariable Long id) {
        rentalService.cancelRental(id);
        return "redirect:/all-rentals";
    }
}