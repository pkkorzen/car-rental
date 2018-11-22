package com.example.carrentalapp.services.impl;

import com.example.carrentalapp.dto.UserDto;
import com.example.carrentalapp.services.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserServiceImplTest {

    @Autowired
    private UserService userService;
    @Test
    public void shouldFindAllUsers() {
        List<UserDto> users = userService.findAll();
        assertEquals(2, users.size());
    }

    @Test
    public void shouldFindUserByLogin() {
        UserDto userDto = userService.findUserByLogin("janek").get();
        assertEquals("Jan", userDto.getName());
    }

    @Test
    public void shouldFindUserById() {
        UserDto userDto = userService.findUserById(10L).get();
        assertEquals("Kowalski",userDto.getSurname());
    }

    @Test
    public void shouldSaveUser() {
        List<UserDto> usersBeforeSave = userService.findAll();
        UserDto userDto = new UserDto();
        userDto.setName("Kamil");
        userDto.setSurname("Antoni");
        userDto.setCity("Warszawa");
        userService.save(userDto);
        List<UserDto> usersAfterSave = userService.findAll();
        assertEquals(usersBeforeSave.size()+1, usersAfterSave.size());
    }
}