package com.example.carrentalapp.controllers;

import com.example.carrentalapp.converters.UserConverter;
import com.example.carrentalapp.dto.UserDto;
import com.example.carrentalapp.entities.Car;
import com.example.carrentalapp.entities.Location;
import com.example.carrentalapp.entities.Rental;
import com.example.carrentalapp.services.CarService;
import com.example.carrentalapp.services.LocationService;
import com.example.carrentalapp.services.RentalService;
import com.example.carrentalapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Controller
public class RentalController {

    private RentalService rentalService;
    private UserService userService;
    private CarService carService;
    private LocationService locationService;

    @Autowired
    private UserConverter userConverter;

    @Autowired
    public RentalController(RentalService rentalService, UserService userService, CarService carService, LocationService locationService) {
        this.rentalService = rentalService;
        this.userService = userService;
        this.carService = carService;
        this.locationService = locationService;
    }

    @GetMapping("/all-rentals")
    public String showAllRentals(Model model, Authentication authentication) {
        String login = authentication.getName();
        Optional<UserDto> userOptional = userService.findUserByLogin(login);
        UserDto userDto = new UserDto();

        if (userOptional.isPresent()) {
            userDto = userOptional.get();
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

    //ten chyba do wyrzucenia, nie będziemy raczej chcieli dodawać rentali w ten sposób
    @GetMapping("rentals/add")
    public String addRental(Model model) {
        model.addAttribute("text", "Add");
        model.addAttribute("rental", new Rental());
        return "rentals/rental";
    }

    @GetMapping("rentals/edit/{id}")
    public String editRental(@PathVariable Long id, Model model, Authentication authentication) {
        model.addAttribute("text", "Edit");

        List<UserDto> users = userService.findAll();

        Optional<Rental> rentalOptional = rentalService.findRentalById(id);
        rentalOptional.ifPresent(rental -> model.addAttribute("rental", rental));

        LocalDate rentalDate = LocalDate.now();
        LocalDate returnDate = rentalDate.plus(1, ChronoUnit.DAYS);

        if (rentalOptional.isPresent()) {
            Rental rental = rentalOptional.get();
            model.addAttribute("rental", rental);
            rentalDate = rental.getRentalDate();
            returnDate = rental.getReturnDate();
        }

        List<Car> cars = carService.findCarsAvailableByDates(rentalDate, returnDate);
        Optional<UserDto> userOptional = userService.findUserByLogin(authentication.getName());
        userOptional.ifPresent(user -> model.addAttribute("userRole", user.getRole()));
        model.addAttribute("users", users);
        model.addAttribute("cars", cars);
        return "rentals/rental";
    }

    @GetMapping("rentals/delete-confirmation/{id}")
    public String deleteConfirmation(@PathVariable Long id, Model model) {
        Optional<Rental> rentalOptional = rentalService.findRentalById(id);
        rentalOptional.ifPresent(rental -> model.addAttribute("rentalToAsk", rental));
        return "rentals/delete-confirmation";
    }

    @GetMapping("rentals/delete/{id}")
    public String deleteRental(@PathVariable Long id) {
        rentalService.deleteRental(id);
        return "redirect:/all-rentals";
    }

    @GetMapping("rentals/rental-confirmation/{id}/{startDate}/{endDate}/{startLocationId}/{endLocationId}")
    public String confirmRental(@PathVariable Long id, Model model, Authentication authentication,
                                @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                @PathVariable Long startLocationId, @PathVariable Long endLocationId) {

        Rental rental = new Rental();

        String login = authentication.getName();
        Optional<UserDto> userOptional = userService.findUserByLogin(login);
        //do poprawki, jak przejdziemy na rentalDto to mozna wtedy tylko ID zostawic zamiast calego usera i konwersja niepotrzebna
        userOptional.ifPresent(userDto -> rental.setUser(userConverter.apply(userDto)));

        Optional<Location> rentalLocationOptional = locationService.findLocationById(startLocationId);
        rentalLocationOptional.ifPresent(rental::setRentalPlace);
        Optional<Location> returnLocationOptional = locationService.findLocationById(endLocationId);
        returnLocationOptional.ifPresent(rental::setReturnPlace);
        rental.setRentalDate(startDate);
        rental.setReturnDate(endDate);

        Optional<Car> carOptional = carService.findCarById(id);
        carOptional.ifPresent(rental::setCar);

        model.addAttribute("rental", rental);
        return "rentals/rental-confirmation";

    }
}