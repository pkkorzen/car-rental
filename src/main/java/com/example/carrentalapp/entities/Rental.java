package com.example.carrentalapp.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @JoinColumn(name = "id_user")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private User user;
    @ManyToOne
    @JoinColumn(name ="id_car")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Car car;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate rentalDate;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate returnDate;
    @ManyToOne
    @JoinColumn(name = "rental_place")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Location rentalPlace;
    @ManyToOne
    @JoinColumn(name = "return_place")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Location returnPlace;
    @ManyToOne
    @JoinColumn(name = "id_status")
    private RentalStatus rentalStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public Location getRentalPlace() {
        return rentalPlace;
    }

    public void setRentalPlace(Location rentalPlace) {
        this.rentalPlace = rentalPlace;
    }

    public Location getReturnPlace() {
        return returnPlace;
    }

    public void setReturnPlace(Location returnPlace) {
        this.returnPlace = returnPlace;
    }

    public RentalStatus getRentalStatus() {
        return rentalStatus;
    }

    public void setRentalStatus(RentalStatus rentalStatus) {
        this.rentalStatus = rentalStatus;
    }
}
