package com.backend.restaurant_service.services;

import com.backend.restaurant_service.entities.Customise;
import com.backend.restaurant_service.entities.Menu;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;

public interface MenuService {

    Menu createMenu(Menu menu,MultipartFile menuImage);

    List<Menu> getAllMenus();

    void deleteMenuItem(String menuId);

    Menu addCustomisation(String menuId, List<Customise> customise);

    Menu deleteCustomisation(String menuId, List<Customise> customise);
}
