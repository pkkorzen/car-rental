package com.example.carrentalapp.security.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/", "/resources/**", "/js/**", "/css/**", "/img/**").permitAll()
                .antMatchers("/all-cars", "/available-cars").hasAnyRole("ADMIN", "USER")
                .antMatchers("/cars/**", "/all-users").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/user/login")
                .loginProcessingUrl("/login")
                .usernameParameter("login")
                .passwordParameter("password")
                .defaultSuccessUrl("/")
                .permitAll();

    }
}
