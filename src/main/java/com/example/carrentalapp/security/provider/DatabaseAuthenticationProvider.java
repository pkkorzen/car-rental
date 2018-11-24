package com.example.carrentalapp.security.provider;

import com.example.carrentalapp.dto.UserDto;
import com.example.carrentalapp.entities.User;
import com.example.carrentalapp.repositories.UserRepository;
import com.example.carrentalapp.services.LoginService;
import com.example.carrentalapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DatabaseAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private LoginService loginService;
    @Autowired
    private UserService userService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String login = authentication.getName();
        String password = authentication.getCredentials().toString();
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        Optional<UserDto> userOptional = userService.findUserByLoginAndPassword(login, password);
        userOptional.ifPresent(user -> grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole())));
        boolean logged = loginService.login(login, password);
        if(logged){
            return new UsernamePasswordAuthenticationToken(login, password, grantedAuthorities);
        } else{
            return null;
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
