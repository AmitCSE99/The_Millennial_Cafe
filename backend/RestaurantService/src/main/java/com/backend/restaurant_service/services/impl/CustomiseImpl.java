package com.backend.restaurant_service.services.impl;

import com.backend.restaurant_service.entities.Customise;
import com.backend.restaurant_service.respositories.CustomiseRepository;
import com.backend.restaurant_service.services.CustomiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CustomiseImpl implements CustomiseService {

    @Autowired
    private CustomiseRepository customiseRepository;

    @Override
    public Customise addCustomisation(Customise customise) {
        String generatedId= UUID.randomUUID().toString();
        customise.setCustomiseId(generatedId);
        return customiseRepository.save(customise);
    }

    @Override
    public void deleteCustomisation(String customiseId) {
        customiseRepository.deleteById(customiseId);
    }
}
