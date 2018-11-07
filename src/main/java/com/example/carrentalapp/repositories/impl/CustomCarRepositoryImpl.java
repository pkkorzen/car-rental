package com.example.carrentalapp.repositories.impl;

import com.example.carrentalapp.entities.Car;
import com.example.carrentalapp.repositories.CustomCarRepository;
import org.springframework.stereotype.Repository;


import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Repository(value = "customCarRepository")
public class CustomCarRepositoryImpl implements CustomCarRepository {

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Car> findCarsAvaialbleBetweenDates(LocalDate startDate, LocalDate endDate) {

        Query query = em.createQuery("SELECT c FROM Car c LEFT JOIN Rental r ON c.id = r.car.id WHERE r.rentalDate > ?1 OR r.plannedDate < ?2 OR r.id is null");
        query.setParameter(1, endDate);
        query.setParameter(2, startDate);
        List<Car> cars = query.getResultList();
        return cars;
    }
}
