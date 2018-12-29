package com.example.carrentalapp.controllers;

import com.example.carrentalapp.dto.UserDto;
import com.example.carrentalapp.entities.Location;
import com.example.carrentalapp.services.LocationService;
import com.example.carrentalapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class LocationController {

    private LocationService locationService;
    private UserService userService;

    @Autowired
    public LocationController(LocationService locationService, UserService userService){
        this.locationService = locationService;
        this.userService = userService;
    }

    @GetMapping("/all-locations")
    public String showLocations(Model model, Authentication authentication){
        List<Location> locations = locationService.findAllLocations();
        model.addAttribute("locations", locations);
        setUserRoleAttribute(model, authentication);
        return "locations/all-locations";
    }

    @PostMapping("locations/save")
    public String saveLocation(@ModelAttribute Location location, @RequestParam("pressed-button") String pushedButton){

        if (pushedButton.equalsIgnoreCase("save")){
            locationService.saveLocation(location);
        }

        return "redirect:/all-locations";
    }

    @GetMapping("locations/add")
    public String addLocation(Model model, Authentication authentication){
        model.addAttribute("text", "New");
        model.addAttribute("location", new Location());
        setUserRoleAttribute(model, authentication);
        return "locations/location";
    }

    @GetMapping("locations/edit/{id}")
    public String editLocation(@PathVariable Long id, Model model, Authentication authentication){
        model.addAttribute("text", "Edit");
        Optional<Location> locationOptional = locationService.findLocationById(id);
        locationOptional.ifPresent(location -> model.addAttribute("location", location));
        setUserRoleAttribute(model, authentication);
        return "locations/location";
    }

    @GetMapping("locations/delete-confirmation/{id}")
    public String deleteConfirmation(@PathVariable Long id, Model model, Authentication authentication){
        Optional<Location> locationOptional = locationService.findLocationById(id);
        locationOptional.ifPresent(location -> model.addAttribute("locationToAsk", location));
        setUserRoleAttribute(model, authentication);
        return "locations/delete-confirmation";
    }

    @GetMapping("locations/delete/{id}")
    public String deleteLocation(@PathVariable Long id){
        locationService.deleteLocation(id);
        return "redirect:/all-locations";
    }

    private void setUserRoleAttribute(Model model, Authentication authentication) {
        Optional<UserDto> userOptional = userService.findUserByLogin(authentication.getName());
        userOptional.ifPresent(user -> model.addAttribute("userRole", user.getRole()));
    }
}
