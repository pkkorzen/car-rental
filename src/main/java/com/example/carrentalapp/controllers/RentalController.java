package com.example.carrentalapp.controllers;

import com.example.carrentalapp.entities.Car;
import com.example.carrentalapp.entities.Rental;
import com.example.carrentalapp.entities.User;
import com.example.carrentalapp.services.CarService;
import com.example.carrentalapp.services.RentalService;
import com.example.carrentalapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    public RentalController(RentalService rentalService, UserService userService, CarService carService) {
        this.rentalService = rentalService;
        this.userService = userService;
        this.carService = carService;
    }

    @GetMapping("/all-rentals")
    public String showAllRentals(Model model, Authentication authentication) {
        String login = authentication.getName();
        Optional<User> userOptional = userService.findUserByLogin(login);
        User user = new User();

        if (userOptional.isPresent()) {
            user = userOptional.get();
        }

        List<Rental> rentals;

        if ("ROLE_ADMIN".equals(user.getRole())) {
            rentals = rentalService.findAllRentals();
            model.addAttribute("text", "All");
        } else {
            rentals = rentalService.findAllRentalsByUser(user);
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

        List<User> users = userService.findAll();

        Optional<Rental> rentalOptional = rentalService.findRentalById(id);
        rentalOptional.ifPresent(rental -> model.addAttribute("rental", rental));

        LocalDate rentalDate = LocalDate.now();
        LocalDate plannedDate = rentalDate.plus(1, ChronoUnit.DAYS);

        if (rentalOptional.isPresent()) {
            Rental rental = rentalOptional.get();
            model.addAttribute("rental", rental);
            rentalDate = rental.getRentalDate();
            plannedDate = rental.getPlannedDate();
        }

        List<Car> cars = carService.findCarsAvailableBetweenDates(rentalDate, plannedDate);
        Optional<User> userOptional = userService.findUserByLogin(authentication.getName());
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

    @GetMapping("rentals/rental-confirmation/{id}/{startDate}/{endDate}")
    public String confirmRental(@PathVariable Long id, @PathVariable String startDate, @PathVariable String endDate,
                                Model model, Authentication authentication) {

        Rental rental = new Rental();

        String login = authentication.getName();
        Optional<User> userOptional = userService.findUserByLogin(login);
        userOptional.ifPresent(rental::setUser);

        LocalDate rentalDate = LocalDate.parse(startDate);
        LocalDate plannedDate = LocalDate.parse(endDate);

        rental.setRentalDate(rentalDate);
        rental.setPlannedDate(plannedDate);

        Optional<Car> carOptional = carService.findCarById(id);
        carOptional.ifPresent(rental::setCar);

        model.addAttribute("rental", rental);
        return "rentals/rental-confirmation";

    }
}