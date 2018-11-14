package com.example.carrentalapp.controllers;

import com.example.carrentalapp.dto.UserDto;
import com.example.carrentalapp.entities.User;
import com.example.carrentalapp.repositories.UserRepository;
import com.example.carrentalapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class UserController {

    private UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }
    @GetMapping("/user/login")
    public String loginUser(){
        return "login";
    }

    @PostMapping("user/save")
    public String saveUser(@ModelAttribute UserDto userDto, @RequestParam ("pressed-button") String pushedButton){
        if (pushedButton.equalsIgnoreCase("save")){
            userService.save(userDto);
        }
        return"/";
    }

    @GetMapping("user/edit/{id}")
    public String editUser(@PathVariable Long id, Model model){
        model.addAttribute("text", "Edit");
        Optional<UserDto> userOptional = userService.findUserById(id);
        userOptional.ifPresent(user -> model.addAttribute("user", user));
        return "user/user";
    }
}
