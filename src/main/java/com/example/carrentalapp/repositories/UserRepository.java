package com.example.carrentalapp.repositories;

import com.example.carrentalapp.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findUserByLoginAndPassword(String login, String password);
    Optional<User> findUserByLogin(String login);
}
