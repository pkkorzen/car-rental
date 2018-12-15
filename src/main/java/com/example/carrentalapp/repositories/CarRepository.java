package com.example.carrentalapp.repositories;

import com.example.carrentalapp.entities.Car;
import com.example.carrentalapp.entities.Location;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CarRepository extends CrudRepository<Car, Long> {

    @Query(value = "SELECT c FROM Car c WHERE c.id NOT IN " +
            "(SELECT ca.id FROM Car ca LEFT JOIN Rental r ON ca.id = r.car.id " +
            "WHERE (r.rentalDate < :endDate AND r.returnDate > :startDate AND r.rentalStatus.status <> 'cancelled') OR r.rentalDate < :endDate AND r.rentalStatus.status = 'rented') " +
            "AND c.id IN " +
            "(SELECT ca.id FROM Car ca LEFT JOIN Rental r ON ca.id = r.car.id " +
            "WHERE r.returnDate = " +
            "(SELECT MAX(ra.returnDate) FROM Rental ra WHERE ra.returnDate < :startDate AND r.car.id = ra.car.id AND ra.rentalStatus.status <> 'cancelled') " +
            "AND r.returnPlace = :startLocation AND r.rentalStatus.status <> 'cancelled') " +
            "AND c.id NOT IN " +
            "(SELECT ca.id FROM Car ca LEFT JOIN Rental r ON ca.id = r.car.id " +
            "WHERE r.rentalDate = :nextDate AND r.rentalPlace <> :endLocation AND r.rentalStatus.status = 'reserved')" +
            "AND c.available = true " +
            "OR c.id IN (SELECT ca.id FROM Car ca LEFT JOIN Rental r ON ca.id = r.car.id WHERE r.car.id is null AND ca.available = true)" +
            "OR c.id NOT IN (SELECT ca.id FROM Car ca LEFT JOIN Rental r ON ca.id = r.car.id WHERE r.rentalStatus.status <> 'cancelled') AND c.available = true")
    List<Car> findCarsAvailableByDatesAndLocation(@Param("startDate") LocalDate startDate,
                                                  @Param("endDate")LocalDate endDate,
                                                  @Param("nextDate")LocalDate nextDate,
                                                  @Param("startLocation") Location startLocation,
                                                  @Param("endLocation") Location endLocation);
}
