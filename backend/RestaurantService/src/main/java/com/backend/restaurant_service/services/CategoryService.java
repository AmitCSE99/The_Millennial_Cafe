package com.backend.restaurant_service.services;

import com.backend.restaurant_service.entities.Category;
import com.backend.restaurant_service.entities.Menu;

import java.util.List;

public interface CategoryService {

    Category createCategory(Category category);
    Category getCategory(String categoryId);
    Category getCategoryByCategoryType(int categoryType);
    Category deleteCategory(String categoryId);

    List<Category> getAllCategories();
}
