package com.backend.user_service.services.impl;

import com.backend.user_service.entities.Cart;
import com.backend.user_service.repositories.CartRepository;
import com.backend.user_service.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart addToCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public void removeFromCart(String cartId) {
        cartRepository.deleteById(cartId);
    }
}
