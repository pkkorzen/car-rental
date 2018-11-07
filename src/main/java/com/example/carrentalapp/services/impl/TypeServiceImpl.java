package com.example.carrentalapp.services.impl;

import com.example.carrentalapp.entities.Type;
import com.example.carrentalapp.repositories.TypeRepository;
import com.example.carrentalapp.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class TypeServiceImpl implements TypeService {

    private TypeRepository typeRepository;

    @Autowired
    public TypeServiceImpl(TypeRepository typeRepository){
        this.typeRepository = typeRepository;
    }

    @Override
    public Optional<Type> findTypeById(Long id) {
        return typeRepository.findById(id);
    }

    @Override
    public List<Type> findAll() {
        Iterable<Type> types = typeRepository.findAll();
        return StreamSupport.stream(types.spliterator(), true).collect(Collectors.toList());
    }

    @Override
    public void saveType(Type type) {
        typeRepository.save(type);
    }

    @Override
    public void deleteType(Long id) {
        Optional<Type> result = typeRepository.findById(id);
        result.ifPresent(type -> typeRepository.delete(type));
    }
}
