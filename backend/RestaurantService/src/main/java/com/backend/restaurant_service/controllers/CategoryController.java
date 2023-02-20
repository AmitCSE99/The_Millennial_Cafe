package com.backend.restaurant_service.controllers;

import com.backend.restaurant_service.entities.Category;
import com.backend.restaurant_service.response_generators.ResponseGenerator;
import com.backend.restaurant_service.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category){
        return ResponseEntity.ok(categoryService.createCategory(category));
    }

    @GetMapping("/get-category")
    public ResponseEntity<Category> getCategory(@RequestParam String categoryId){
        return ResponseEntity.ok(categoryService.getCategory(categoryId));
    }

    @GetMapping
    public ResponseEntity<Map<String,Object>> getAllCategories(){
        List<Category> categories=categoryService.getAllCategories();
        return ResponseGenerator.generateSuccessResponse(HttpStatus.OK,categories);
    }
}
