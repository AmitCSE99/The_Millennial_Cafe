package com.backend.restaurant_service.services.impl;

import com.backend.restaurant_service.cloudinary.CloudinaryInstance;
import com.backend.restaurant_service.entities.Customise;
import com.backend.restaurant_service.entities.Menu;
import com.backend.restaurant_service.exceptions.ResourceNotFoundException;
import com.backend.restaurant_service.respositories.CategoryRepository;
import com.backend.restaurant_service.respositories.MenuRepository;
import com.backend.restaurant_service.services.MenuService;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuRepository menuRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Menu createMenu(Menu menu,MultipartFile menuImage) {
        String generatedId= UUID.randomUUID().toString();
        menu.setMenuId(generatedId);
        Cloudinary cloudinary= CloudinaryInstance.getInstance();
        try{
            Map uploadResult=cloudinary.uploader().upload(menuImage.getBytes(), ObjectUtils.emptyMap());
            String publicId = uploadResult.get("url").toString();
            menu.setMenuImageUrl(publicId);
            System.out.println(uploadResult);
        }catch(Exception e){
            e.printStackTrace();
        }
        return menuRepository.save(menu);
    }

    @Override
    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }


    @Override
    public void deleteMenuItem(String menuId) {
        menuRepository.deleteById(menuId);
    }

    @Override
    public Menu addCustomisation(String menuId, List<Customise> customise) {
        Menu menu=menuRepository.findById(menuId).orElseThrow(()->new ResourceNotFoundException("The Menu item is not available"));
        List<Customise> presentCustomisation=menu.getCustomisations();
        presentCustomisation.addAll(customise);
        menu.setCustomisations(presentCustomisation);
        return menuRepository.save(menu);

    }

    @Override
    public Menu deleteCustomisation(String menuId, List<Customise> customise) {
        Menu menu=menuRepository.findById(menuId).orElseThrow(()->new ResourceNotFoundException("The Menu item is not available"));
        List<Customise> presentCustomisation=menu.getCustomisations();
        presentCustomisation.removeAll(customise);
        menu.setCustomisations(presentCustomisation);
        return menuRepository.save(menu);
    }
}
