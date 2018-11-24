package com.example.carrentalapp.converters;

import com.example.carrentalapp.dto.UserDto;
import com.example.carrentalapp.entities.User;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class UserDtoConverter implements Function<User, UserDto> {

    @Override
    public UserDto apply(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setSurname(user.getSurname());
        userDto.setMail(user.getMail());
        userDto.setLogin(user.getLogin());
        userDto.setPhone(user.getPhone());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getUserRole().getRole());
        userDto.setRoleId(user.getUserRole().getId());
        userDto.setCity(user.getAddress().getCity());
        userDto.setStreet(user.getAddress().getStreet());
        userDto.setNumber(user.getAddress().getNumber());
        userDto.setZipCode(user.getAddress().getZipCode());
        userDto.setAddressId(user.getAddress().getId());
        return userDto;
    }
}
