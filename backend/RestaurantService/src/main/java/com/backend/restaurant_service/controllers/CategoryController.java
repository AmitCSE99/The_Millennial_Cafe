package com.backend.restaurant_service.controllers;

import com.backend.restaurant_service.entities.Category;
import com.backend.restaurant_service.response_generators.ResponseGenerator;
import com.backend.restaurant_service.responses.SuccessResponse;
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
    public ResponseEntity<SuccessResponse<Category>> createCategory(@RequestBody Category category){
        Category createdCategory=categoryService.createCategory(category);
        return new ResponseGenerator<Category>().generateSuccessResponse(HttpStatus.CREATED,createdCategory);
    }

    @GetMapping("/get-category")
    public ResponseEntity<SuccessResponse<Category>> getCategory(@RequestParam String categoryId){
        Category category=categoryService.getCategory(categoryId);
        return new ResponseGenerator<Category>().generateSuccessResponse(HttpStatus.OK,category);
    }

    @GetMapping("/public")
    public ResponseEntity<SuccessResponse<List<Category>>> getAllCategories(){
        List<Category> categories=categoryService.getAllCategories();
        return new ResponseGenerator<List<Category>>().generateSuccessResponse(HttpStatus.OK,categories);
    }
}
