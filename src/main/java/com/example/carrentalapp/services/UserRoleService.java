package com.example.carrentalapp.services;

import com.example.carrentalapp.entities.UserRole;

import java.util.Optional;

public interface UserRoleService {
    Optional<UserRole> findUserRoleById(Long id);
}
