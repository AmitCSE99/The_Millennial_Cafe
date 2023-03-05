package com.backend.user_service.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "cart")
public class Cart {

    @Id
    @Column(name = "ID")
    private String cartId;

    @Column(name = "MENU_ID",nullable = false)
    private String cartMenuId;

    @Column(name = "MENU_ITEM_NAME",nullable = false)
    private String cartMenuItemName;

    @Column(name = "MENU_ITEM_PRICE",nullable = false)
    @Min(10)
    private int cartMenuItemPrice;

    @Column(name = "MENU_ITEM_QUANTITY",nullable = false)
    @Min(1)
    private int cartMenuItemQuantity;

    @Column(name = "MENU_ITEM_DISCOUNT",nullable = false)
    @Min(0)
    @Max(40)
    private int cartMenuItemDiscount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "USER_ID")
    @JsonBackReference
    private User user;


}
