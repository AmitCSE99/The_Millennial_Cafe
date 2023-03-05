package com.backend.user_service.services;

import com.backend.user_service.entities.Cart;

public interface CartService {

    Cart addToCart(Cart cart);

    void removeFromCart(String cartId);
}
