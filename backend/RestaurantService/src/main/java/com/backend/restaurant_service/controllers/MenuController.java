package com.backend.restaurant_service.controllers;

import com.backend.restaurant_service.entities.AuthRequest;
import com.backend.restaurant_service.entities.Menu;
import com.backend.restaurant_service.external_entities.Cart;
import com.backend.restaurant_service.external_services.CartService;
import com.backend.restaurant_service.response_generators.ResponseGenerator;
import com.backend.restaurant_service.responses.SuccessResponse;
import com.backend.restaurant_service.services.MenuService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Autowired
    private CartService cartService;

    @PostMapping
    public ResponseEntity<SuccessResponse<Menu>> createMenu(@RequestParam("menuData") String menuData,@RequestParam("menuImage")MultipartFile menuImage) throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        Menu menu=objectMapper.readValue(menuData,Menu.class);
        Menu createdMenu=menuService.createMenu(menu,menuImage);
        return new ResponseGenerator<Menu>().generateSuccessResponse(HttpStatus.CREATED,menu);
    }

    @GetMapping("/public")
    public ResponseEntity<SuccessResponse<List<Menu>>> getAllMenus(){
        List<Menu> menuItems=menuService.getAllMenus();
        return new ResponseGenerator<List<Menu>>().generateSuccessResponse(HttpStatus.OK,menuItems);
    }

    @DeleteMapping("/{menuId}")
    public ResponseEntity<SuccessResponse<String>> deleteMenu(@PathVariable String menuId){
        menuService.deleteMenuItem(menuId);
        return new ResponseGenerator<String>().generateSuccessResponse(HttpStatus.OK,"The Menu has been deleted");
    }

    @PostMapping("/add-customisation")
    public ResponseEntity<SuccessResponse<Menu>> addCustomisation(@RequestBody AuthRequest request){
        Menu menu=menuService.addCustomisation(request.getMenuId(),request.getCustomisations());
        return new ResponseGenerator<Menu>().generateSuccessResponse(HttpStatus.CREATED,menu);
    }

    @PostMapping("/delete-customisation")
    public ResponseEntity<SuccessResponse<Menu>> deleteCustomisation(@RequestBody AuthRequest request){
        Menu menu=menuService.deleteCustomisation(request.getMenuId(),request.getCustomisations());
        return new ResponseGenerator<Menu>().generateSuccessResponse(HttpStatus.OK,menu);
    }

    @PostMapping("/add-to-cart")
    public ResponseEntity<SuccessResponse<Cart>> addItemToCart(@RequestBody Cart cart){
        Cart addedCart=cartService.addMenuToCart(cart);
        return new ResponseGenerator<Cart>().generateSuccessResponse(HttpStatus.CREATED,addedCart);
    }
}
