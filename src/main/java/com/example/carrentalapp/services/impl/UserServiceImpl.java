package com.example.carrentalapp.services.impl;

import com.example.carrentalapp.converters.UserConverter;
import com.example.carrentalapp.converters.UserDtoConverter;
import com.example.carrentalapp.dto.UserDto;
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
    private UserConverter userConverter;
    private UserDtoConverter userDtoConverter;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserConverter userConverter,
                           UserDtoConverter userDtoConverter){
        this.userRepository = userRepository;
        this.userConverter = userConverter;
        this.userDtoConverter = userDtoConverter;
    }

    @Override
    public List<UserDto> findAll() {
        Iterable<User> users = userRepository.findAll();
        return StreamSupport.stream(users.spliterator(), true)
                .map(user -> userDtoConverter.apply(user))
                .collect(Collectors.toList());
    }
    @Override
    public Optional<UserDto> findUserByLogin(String login) {
        Optional<User> userOptional = userRepository.findUserByLogin(login);
        return userOptional.map(user -> userDtoConverter.apply(user));
    }

    @Override
    public Optional<UserDto> findUserByLoginAndPassword(String login, String password) {
        Optional<User> userOptional = userRepository.findUserByLoginAndPassword(login, password);
        return userOptional.map(user -> userDtoConverter.apply(user));
    }

    @Override
    public Optional<UserDto> findUserById(Long id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.map(user -> userDtoConverter.apply(user));
    }

    @Override
    public void save(UserDto userDto) {
        userRepository.save(userConverter.apply(userDto));
    }
}
