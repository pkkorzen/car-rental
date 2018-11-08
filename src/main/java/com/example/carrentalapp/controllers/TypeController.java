package com.example.carrentalapp.controllers;

import com.example.carrentalapp.entities.Rental;
import com.example.carrentalapp.entities.Type;
import com.example.carrentalapp.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class TypeController {

    private TypeService typeService;

    @Autowired
    public TypeController(TypeService typeService){
        this.typeService = typeService;
    }

    @GetMapping("/all-types")
    public String showAllTypes(Model model){
        List<Type> types = typeService.findAll();
        model.addAttribute("types", types);
        return "types/all";
    }

    @PostMapping("types/save")
    public String saveType(@ModelAttribute Type type, @RequestParam("pressed-button") String pushedButton){

        if (pushedButton.equalsIgnoreCase("save")){
            typeService.saveType(type);
        }

        return "redirect:/all-types";
    }

    @GetMapping("types/add")
    public String addType(Model model){
        model.addAttribute("text", "Add");
        model.addAttribute("type", new Type());
        return "types/type";
    }

    @GetMapping("types/edit/{id}")
    public String editType(@PathVariable Long id, Model model){
        model.addAttribute("text", "Edit");

        Optional<Type> typeOptional = typeService.findTypeById(id);
        typeOptional.ifPresent(type -> typeService.saveType(type));
        return "types/type";
    }

    @GetMapping("types/delete-confirmation/{id}")
    public String deleteConfirmation(@PathVariable Long id, Model model){
        Optional<Type> typeOptional = typeService.findTypeById(id);
        typeOptional.ifPresent(type -> model.addAttribute("typeToAsk", type));
        return "types/delete-confirmation";
    }

    @GetMapping("types/delete/{id}")
    public String deleteType(@PathVariable Long id){
        typeService.deleteType(id);
        return "redirect:/all-types";
    }
}
