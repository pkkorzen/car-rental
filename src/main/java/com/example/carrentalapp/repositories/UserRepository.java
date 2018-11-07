package com.example.carrentalapp.repositories;

import com.example.carrentalapp.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
