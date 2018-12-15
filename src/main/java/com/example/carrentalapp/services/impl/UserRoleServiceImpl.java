package com.example.carrentalapp.services.impl;

import com.example.carrentalapp.entities.UserRole;
import com.example.carrentalapp.repositories.UserRoleRepository;
import com.example.carrentalapp.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserRoleServiceImpl implements UserRoleService {

    private UserRoleRepository userRoleRepository;

    @Autowired
    public UserRoleServiceImpl(UserRoleRepository userRoleRepository){
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    public Optional<UserRole> findUserRoleById(Long id) {
        return userRoleRepository.findById(id);
    }
}
