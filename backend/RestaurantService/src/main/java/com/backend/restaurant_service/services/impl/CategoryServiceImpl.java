package com.backend.restaurant_service.services.impl;

import com.backend.restaurant_service.entities.Category;
import com.backend.restaurant_service.entities.Menu;
import com.backend.restaurant_service.respositories.CategoryRepository;
import com.backend.restaurant_service.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category getCategory(String categoryId) {
        return categoryRepository.findById(categoryId).orElseThrow(()->new RuntimeException("The Category is not available"));
    }

    @Override
    public Category getCategoryByCategoryType(int categoryType) {
        return null;
    }

    @Override
    public Category deleteCategory(String categoryId) {
        return null;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

}
