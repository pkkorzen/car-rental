package com.example.carrentalapp.controllers;

import com.example.carrentalapp.dto.UserDto;
import com.example.carrentalapp.entities.Location;
import com.example.carrentalapp.services.LocationService;
import com.example.carrentalapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Optional;

@Controller
public class MainPageController {

    private LocationService locationService;
    private UserService userService;

    @Autowired
    public MainPageController(LocationService locationService, UserService userService) {
        this.locationService = locationService;
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model, Authentication authentication) {
        List<Location> locations = locationService.findAllLocations();
        model.addAttribute("locations", locations);
        String role = "ROLE_GUEST";
        if (authentication != null) {
            Optional<UserDto> userDtoOptional = userService.findUserByLogin(authentication.getName());
            if (userDtoOptional.isPresent()) {
                role = userDtoOptional.get().getRole();
            }
        }
        model.addAttribute("userRole", role);
        return "../static/index";
    }
}
