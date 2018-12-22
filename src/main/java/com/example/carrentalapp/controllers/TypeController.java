package com.example.carrentalapp.controllers;

import com.example.carrentalapp.dto.UserDto;
import com.example.carrentalapp.entities.Rental;
import com.example.carrentalapp.entities.Type;
import com.example.carrentalapp.services.TypeService;
import com.example.carrentalapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class TypeController {

    private TypeService typeService;
    private UserService userService;

    @Autowired
    public TypeController(TypeService typeService, UserService userService){
        this.typeService = typeService;
        this.userService = userService;
    }

    @GetMapping("/all-types")
    public String showAllTypes(Model model, Authentication authentication){
        List<Type> types = typeService.findAll();
        model.addAttribute("types", types);

        getUserRole(model, authentication);
        return "types/all-types";
    }

    private void getUserRole(Model model, Authentication authentication) {
        String login = authentication.getName();
        Optional<UserDto> userOptional = userService.findUserByLogin(login);
        UserDto userDto;

        if (userOptional.isPresent()) {
            userDto = userOptional.get();
            model.addAttribute("userRole", userDto.getRole());
        }
    }

    @PostMapping("types/save")
    public String saveType(@ModelAttribute Type type, @RequestParam("pressed-button") String pushedButton){

        if (pushedButton.equalsIgnoreCase("save")){
            typeService.saveType(type);
        }

        return "redirect:/all-types";
    }

    @GetMapping("types/add")
    public String addType(Model model, Authentication authentication){
        model.addAttribute("text", "Add");
        model.addAttribute("type", new Type());
        getUserRole(model, authentication);
        return "types/type";
    }

    @GetMapping("types/edit/{id}")
    public String editType(@PathVariable Long id, Model model, Authentication authentication){
        model.addAttribute("text", "Edit");

        Optional<Type> typeOptional = typeService.findTypeById(id);
        typeOptional.ifPresent(type -> model.addAttribute("type",type));
        getUserRole(model, authentication);
        return "types/type";
    }
}
