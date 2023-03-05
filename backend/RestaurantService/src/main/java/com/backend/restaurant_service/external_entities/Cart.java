package com.backend.restaurant_service.external_entities;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class Cart {
    private String cartId;

    private String cartMenuId;

    private String cartMenuItemName;

    private int cartMenuItemPrice;

    private int cartMenuItemQuantity;

    private int cartMenuItemDiscount;

    private User user;
}
