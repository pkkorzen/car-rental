package com.example.carrentalapp.services.impl;

import com.example.carrentalapp.entities.User;
import com.example.carrentalapp.repositories.UserRepository;
import com.example.carrentalapp.services.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DatabaseLoginService implements LoginService {

    private UserRepository userRepository;

    @Autowired
    public DatabaseLoginService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    @Override
    public boolean login(String login, String password) {
        Optional<User> user = userRepository.findUserByLoginAndPassword(login, password);
        return user.isPresent();
    }
}
