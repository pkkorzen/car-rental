package com.example.carrentalapp.converters;

import com.example.carrentalapp.dto.UserDto;
import com.example.carrentalapp.entities.Address;
import com.example.carrentalapp.entities.User;
import com.example.carrentalapp.entities.UserRole;
import com.example.carrentalapp.services.AddressService;
import com.example.carrentalapp.services.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Function;

@Component
public class UserConverter implements Function<UserDto, User> {

    @Autowired
    private AddressService addressService;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public User apply(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setSurname(userDto.getSurname());
        user.setPhone(userDto.getPhone());
        user.setMail(userDto.getMail());
        user.setLogin(userDto.getLogin());
        user.setPassword(userDto.getPassword());

        Optional<UserRole> userRoleOptional;
        if(userDto.getId()!=null){
            user.setId(userDto.getId());
            Optional<Address> addressOptional = addressService.findAddressById(userDto.getAddressId());
            if(addressOptional.isPresent()){
                Address address = addressOptional.get();
                setUserAddress(userDto, user, address);
            }
            userRoleOptional = userRoleService.findUserRoleById(userDto.getRoleId());
            userRoleOptional.ifPresent(user::setUserRole);
        } else {
            Address address = new Address();
            setUserAddress(userDto, user, address);
            userRoleOptional = userRoleService.findUserRoleById(10L);
            userRoleOptional.ifPresent(user::setUserRole);
        }
        return user;
    }

    private void setUserAddress(UserDto userDto, User user, Address address) {
        address.setCity(userDto.getCity());
        address.setNumber(userDto.getNumber());
        address.setStreet(userDto.getStreet());
        address.setZipCode(userDto.getZipCode());
        user.setAddress(address);
    }
}
