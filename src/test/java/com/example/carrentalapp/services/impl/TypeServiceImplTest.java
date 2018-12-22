package com.example.carrentalapp.services.impl;

import com.example.carrentalapp.entities.Type;
import com.example.carrentalapp.services.TypeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
@SpringBootTest
@RunWith(SpringRunner.class)
public class TypeServiceImplTest {

    @Autowired
    private TypeService typeService;

    @Test
    public void shouldFindTypeById() {
        Type type = typeService.findTypeById(10L).get();
        assertEquals("A", type.getTypeName());
    }

    @Test
    public void shouldFindAllTypes() {
        List<Type> types = typeService.findAll();
        assertEquals(3, types.size());
    }

    @Test
    public void shouldSaveType() {
        List<Type> typesBeforeSave = typeService.findAll();
        Type type = new Type();
        type.setPrice(BigDecimal.valueOf(169.90));
        type.setTypeName("D");
        typeService.saveType(type);
        List<Type> typesAfterSave = typeService.findAll();
        assertEquals(typesBeforeSave.size()+1, typesAfterSave.size());
    }
}