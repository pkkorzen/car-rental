package com.example.carrentalapp.controllers;

import com.example.carrentalapp.dto.UserDto;
import com.example.carrentalapp.entities.User;
import com.example.carrentalapp.repositories.UserRepository;
import com.example.carrentalapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @GetMapping("all-users")
    public String showAllUsers(Model model){
        List<UserDto> userDtos = userService.findAll();
        model.addAttribute("users", userDtos);
        return "user/all-users";
    }

    @PostMapping("user/save")
    public String saveUser(@ModelAttribute UserDto userDto, @RequestParam ("pressed-button") String pushedButton){
        if (pushedButton.equalsIgnoreCase("save")){
            userService.save(userDto);
        }
        return "../static/index";
    }

    @GetMapping("user/edit/{id}")
    public String editUser(@PathVariable Long id, Model model){
        model.addAttribute("text", "Edit");
        Optional<UserDto> userOptional = userService.findUserById(id);
        userOptional.ifPresent(user -> model.addAttribute("user", user));
        return "user/user";
    }

    @GetMapping("user/delete-confirmation/{id}")
    public String deleteConfirmation(@PathVariable Long id, Model model){
        Optional<UserDto> userDtoOptional = userService.findUserById(id);
        userDtoOptional.ifPresent(userDto -> model.addAttribute("userToAsk", userDto));
        return "user/delete-confirmation";
    }

    @GetMapping("user/delete/{id}")
    public String deleteUser(@PathVariable Long id){

        userService.deleteUser(id);

        return "user/all-users";
    }
}
