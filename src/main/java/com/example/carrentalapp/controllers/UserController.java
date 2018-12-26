package com.example.carrentalapp.controllers;

import com.example.carrentalapp.dto.UserDto;
import com.example.carrentalapp.entities.UserRole;
import com.example.carrentalapp.services.UserRoleService;
import com.example.carrentalapp.services.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class UserController {

    private UserService userService;
    private UserRoleService userRoleService;

    public UserController(UserService userService, UserRoleService userRoleService){
        this.userService = userService;
        this.userRoleService = userRoleService;
    }

    @GetMapping("/user/login")
    public String loginUser(){
        return "login";
    }

    @GetMapping("all-users")
    public String showAllUsers(Model model, Authentication authentication){
        List<UserDto> userDtos = userService.findAll();
        model.addAttribute("users", userDtos);

        setUserRoleAttribute(model, authentication);
        return "user/all-users";
    }

    private void setUserRoleAttribute(Model model, Authentication authentication) {
        Optional<UserDto> userOptional = userService.findUserByLogin(authentication.getName());
        userOptional.ifPresent(user -> model.addAttribute("userRole", user.getRole()));
    }

    @PostMapping("user/save")
    public String saveUser(@ModelAttribute UserDto userDto, @RequestParam ("pressed-button") String pushedButton){
        if (pushedButton.equalsIgnoreCase("save")){
            userService.save(userDto);
        }
        return "redirect:/all-users";
    }

    @GetMapping("user/edit/{id}")
    public String editUser(@PathVariable Long id, Model model, Authentication authentication){
        model.addAttribute("text", "Edit");
        Optional<UserDto> userOptional = userService.findUserById(id);
        userOptional.ifPresent(user -> model.addAttribute("user", user));
        setUserRoleAttribute(model, authentication);
        List<UserRole> userRoles = userRoleService.findAllUserRoles();
        model.addAttribute("userRoles", userRoles);
        return "user/user";
    }

    @GetMapping("user-details")
    public String showUserDetails(Model model, Authentication authentication){
        Optional<UserDto> userOptional = userService.findUserByLogin(authentication.getName());
        userOptional.ifPresent(userDto -> model.addAttribute("user", userDto));
        setUserRoleAttribute(model, authentication);
        return "user/user-details";
    }

    @GetMapping("user/delete-confirmation/{id}")
    public String deleteConfirmation(@PathVariable Long id, Model model, Authentication authentication){
        Optional<UserDto> userDtoOptional = userService.findUserById(id);
        userDtoOptional.ifPresent(userDto -> model.addAttribute("userToAsk", userDto));
        setUserRoleAttribute(model, authentication);
        return "user/delete-confirmation";
    }

    @GetMapping("user/delete/{id}")
    public String deleteUser(@PathVariable Long id){

        userService.deleteUser(id);

        return "redirect:/all-users";
    }
}
