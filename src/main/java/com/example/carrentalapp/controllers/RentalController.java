package com.example.carrentalapp.controllers;

import com.example.carrentalapp.entities.Rental;
import com.example.carrentalapp.services.RentalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class RentalController {

    private RentalService rentalService;

    @Autowired
    public RentalController(RentalService rentalService){
        this.rentalService = rentalService;
    }

    @GetMapping("/all-rentals")
    public String showAllRentals(Model model){
        List<Rental> rentals = rentalService.findAllRentals();
        model.addAttribute("rentals", rentals);
        return "rentals/all-rentals";
    }

    @PostMapping("rentals/save")
    public String saveRental(@ModelAttribute Rental rental, @RequestParam("pressed-button") String pushedButton){

        if (pushedButton.equalsIgnoreCase("save")){
            rentalService.saveRental(rental);
        }

        return "redirect:/all-rentals";
    }

    @GetMapping("rentals/add")
    public String addRental(Model model){
        model.addAttribute("text", "Add");
        model.addAttribute("rental", new Rental());
        return "rentals/rental";
    }

    @GetMapping("rentals/edit/{id}")
    public String editRental(@PathVariable Long id, Model model){
        model.addAttribute("text", "Edit");

        Optional<Rental> rentalOptional = rentalService.findRentalById(id);
        rentalOptional.ifPresent(rental -> rentalService.saveRental(rental));
        return "rentals/rental";
    }

    @GetMapping("rentals/delete-confirmation/{id}")
    public String deleteConfirmation(@PathVariable Long id, Model model){
        Optional<Rental> rentalOptional = rentalService.findRentalById(id);
        rentalOptional.ifPresent(rental -> model.addAttribute("rentalToAsk", rental));
        return "rentals/delete-confirmation";
    }

    @GetMapping("rentals/delete/{id}")
    public String deleteRental(@PathVariable Long id){
        rentalService.deleteRental(id);
        return "redirect:/all-rentals";
    }
}
