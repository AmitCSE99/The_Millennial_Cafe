package com.backend.restaurant_service.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@Table(name = "menu")
public class Menu{

    @Id
    @Column(name = "menu_id")
    private String menuId;

    @Column(name = "menu_name",unique = true)
    private String menuName;

    @Column(name = "menu_description")
    private String menuDescription;

    @Column(name="menu_price")
    private int menuPrice;

    @Column(name = "menu_discount")
    private int menuDiscount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_name")
    @JsonBackReference
    private Category category;

    @ManyToMany(fetch = FetchType.EAGER,targetEntity = Customise.class)
    @JoinTable(name = "menu_customise",joinColumns = {@JoinColumn(name = "menu_id")},inverseJoinColumns = {
            @JoinColumn(name = "customise_id")
    })
    private Set<Customise> customisations=new HashSet<>();
}
