package com.example.carrentalapp.services;

import com.example.carrentalapp.entities.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findUserByLogin(String login);
}
