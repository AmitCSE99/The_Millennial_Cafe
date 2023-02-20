package com.backend.restaurant_service.controllers;

import com.backend.restaurant_service.entities.AuthRequest;
import com.backend.restaurant_service.entities.Menu;
import com.backend.restaurant_service.responses.SuccessResponse;
import com.backend.restaurant_service.services.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @PostMapping
    public ResponseEntity<SuccessResponse<Menu>> createMenu(@RequestBody Menu menu){
        Menu createdMenu=menuService.createMenu(menu);
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>(true,HttpStatus.CREATED.value(),createdMenu));
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<List<Menu>>> getAllMenus(){
        List<Menu> menuItems=menuService.getAllMenus();
        return ResponseEntity.ok(new SuccessResponse<>(true,HttpStatus.OK.value(),menuItems));
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<SuccessResponse<List<Menu>>> getMenuByCategory(@PathVariable String categoryId){
        List<Menu> menuItems=menuService.findByCategory(categoryId);
        return ResponseEntity.ok(new SuccessResponse<>(true,HttpStatus.OK.value(),menuItems));
    }

    @DeleteMapping("/{menuId}")
    public ResponseEntity<SuccessResponse<String>> deleteMenu(@PathVariable String menuId){
        menuService.deleteMenuItem(menuId);
        return ResponseEntity.ok(new SuccessResponse<>(true,HttpStatus.OK.value(), "The Item has been removed"));
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file")MultipartFile file){
        menuService.uploadFile(file);
        return ResponseEntity.ok("uploaded");
    }

    @PostMapping("/add-customisation")
    public ResponseEntity<SuccessResponse<Menu>> addCustomisation(@RequestBody AuthRequest request){
        Menu menu=menuService.addCustomisation(request.getMenuId(),request.getCustomisations());
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>(true,HttpStatus.CREATED.value(),menu));
    }

    @PostMapping("/delete-customisation")
    public ResponseEntity<SuccessResponse<Menu>> deleteCustomisation(@RequestBody AuthRequest request){
        Menu menu=menuService.deleteCustomisation(request.getMenuId(),request.getCustomisations());
        return ResponseEntity.status(HttpStatus.CREATED).body(new SuccessResponse<>(true,HttpStatus.CREATED.value(),menu));
    }
}
