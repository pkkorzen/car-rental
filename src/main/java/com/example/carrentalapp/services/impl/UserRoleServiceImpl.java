package com.example.carrentalapp.services.impl;

import com.example.carrentalapp.entities.UserRole;
import com.example.carrentalapp.repositories.UserRoleRepository;
import com.example.carrentalapp.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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

    @Override
    public List<UserRole> findAllUserRoles() {
        Iterable<UserRole> userRoleIterable = userRoleRepository.findAll();
        return StreamSupport.stream(userRoleIterable.spliterator(), true).collect(Collectors.toList());
    }
}
