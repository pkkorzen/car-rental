package com.example.carrentalapp.controllers;

import com.example.carrentalapp.entities.Location;
import com.example.carrentalapp.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainPageController {

    private LocationService locationService;
    @Autowired
    public MainPageController(LocationService locationService){
        this.locationService = locationService;
    }

    @GetMapping("/")
    public String index(Model model){
        List<Location> locations = locationService.findAllLocations();
        model.addAttribute("locations", locations);
        return "../static/index";
    }
}
