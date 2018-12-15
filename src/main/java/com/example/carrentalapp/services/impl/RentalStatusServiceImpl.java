package com.example.carrentalapp.services.impl;

import com.example.carrentalapp.entities.RentalStatus;
import com.example.carrentalapp.repositories.RentalStatusRepository;
import com.example.carrentalapp.services.RentalStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class RentalStatusServiceImpl implements RentalStatusService {

    private RentalStatusRepository rentalStatusRepository;

    @Autowired
    public RentalStatusServiceImpl(RentalStatusRepository rentalStatusRepository){
        this.rentalStatusRepository = rentalStatusRepository;
    }

    @Override
    public Optional<RentalStatus> findRentalStatusByStatus(String status) {
        return rentalStatusRepository.findRentalStatusByStatus(status);
    }

    @Override
    public List<RentalStatus> findAll() {
        Iterable<RentalStatus> rentalStatuses = rentalStatusRepository.findAll();
        return StreamSupport.stream(rentalStatuses.spliterator(),true).collect(Collectors.toList());
    }

    @Override
    public Optional<RentalStatus> findRentalStatusById(Long id) {
        return rentalStatusRepository.findById(id);
    }
}
