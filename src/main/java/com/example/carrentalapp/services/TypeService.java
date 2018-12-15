package com.example.carrentalapp.services;

import com.example.carrentalapp.entities.Type;

import java.util.List;
import java.util.Optional;

public interface TypeService {
    Optional<Type> findTypeById(Long id);
    List<Type> findAll();
    void saveType(Type type);
}
