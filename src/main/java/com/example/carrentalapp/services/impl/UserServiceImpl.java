package com.example.carrentalapp.services.impl;

import com.example.carrentalapp.entities.User;
import com.example.carrentalapp.repositories.UserRepository;
import com.example.carrentalapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public List<User> findAll() {
        Iterable<User> users = userRepository.findAll();
        return StreamSupport.stream(users.spliterator(), true).collect(Collectors.toList());
    }

    @Autowired
    public UserServiceImpl(UserRepository userRepository){
        this.userRepository = userRepository;
    }
    @Override
    public Optional<User> findUserByLogin(String login) {
        return userRepository.findUserByLogin(login);
    }

    @Override
    public Optional<User> findUserById(Long id) {
        return userRepository.findById(id);
    }
}
