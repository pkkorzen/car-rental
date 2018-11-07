package com.example.carrentalapp.entities;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    @ManyToOne
    @JoinColumn(name = "id_address")
    private Address address;
    private String mail;
    private String phone;
    private String password;
    private String login;
}
