package com.example.carrentalapp.services.impl;

import com.example.carrentalapp.services.LoginService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class DatabaseLoginServiceTest {

    @Autowired
    private LoginService loginService;
    @Test
    public void shouldLoginUser() {
        boolean logged = loginService.login("dawidek", "tromba");
        assertTrue(logged);
    }
}