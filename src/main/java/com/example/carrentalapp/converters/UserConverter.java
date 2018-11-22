package com.example.carrentalapp.converters;

import com.example.carrentalapp.dto.UserDto;
import com.example.carrentalapp.entities.Address;
import com.example.carrentalapp.entities.User;
import com.example.carrentalapp.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

@Component
public class UserConverter implements Function<UserDto, User> {

    @Autowired
    private AddressService addressService;

    @Override
    public User apply(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setPhone(userDto.getPhone());
        user.setMail(userDto.getMail());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());

        if(userDto.getId()!=null){
            user.setId(userDto.getId());
            Optional<Address> addressOptional = addressService.findAddressById(userDto.getAddressId());
            addressOptional.ifPresent(user::setAddress);
            user.setRole(userDto.getRole());
        } else {
            Address address = new Address();
            address.setCity(userDto.getCity());
            address.setNumber(userDto.getNumber());
            address.setStreet(userDto.getStreet());
            address.setZipCode(userDto.getZipCode());
            user.setAddress(address);
        }
        return user;
    }
}
