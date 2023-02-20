package com.backend.restaurant_service.services;

import com.backend.restaurant_service.entities.Customise;

public interface CustomiseService {

    Customise addCustomisation(Customise customise);
    void deleteCustomisation(String customiseId);
}
