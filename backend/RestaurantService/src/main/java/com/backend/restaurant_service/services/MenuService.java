package com.backend.restaurant_service.services;

import com.backend.restaurant_service.entities.Customise;
import com.backend.restaurant_service.entities.Menu;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface MenuService {

    Menu createMenu(Menu menu);

    List<Menu> getAllMenus();

    void deleteMenuItem(String menuId);

    void uploadFile(MultipartFile file);

    Menu addCustomisation(String menuId, Set<Customise> customise);

    Menu deleteCustomisation(String menuId,Set<Customise> customise);
}
