package com.example.carrentalapp.services;

import com.example.carrentalapp.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<User> findUserByLogin(String login);
    Optional<User> findUserById(Long id);
    List<User> findAll();
}
