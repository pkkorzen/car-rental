package com.example.carrentalapp.services.impl;

import com.example.carrentalapp.entities.UserRole;
import com.example.carrentalapp.services.UserRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserRoleServiceImplTest {

    @Autowired
    private UserRoleService userRoleService;

    @Test
    public void shouldFindUserRoleById() {
        UserRole userRole = userRoleService.findUserRoleById(11L).get();
        assertEquals("ROLE_ADMIN", userRole.getRole());
    }
}