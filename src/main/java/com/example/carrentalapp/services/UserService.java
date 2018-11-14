package com.example.carrentalapp.services;

import com.example.carrentalapp.dto.UserDto;
import com.example.carrentalapp.entities.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    Optional<UserDto> findUserByLogin(String login);
    Optional<UserDto> findUserById(Long id);
    List<UserDto> findAll();
    void save(UserDto userDto);
}
