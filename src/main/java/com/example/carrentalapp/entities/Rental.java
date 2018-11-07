package com.example.carrentalapp.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_user")
    private User user;
    @ManyToOne
    @JoinColumn(name ="id_car")
    private Car car;
    private LocalDate rentalDate;
    private LocalDate plannedDate;
    private LocalDate returnDate;
}
