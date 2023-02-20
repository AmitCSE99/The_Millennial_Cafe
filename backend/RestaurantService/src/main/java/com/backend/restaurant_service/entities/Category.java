package com.backend.restaurant_service.entities;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "category")
public class Category {

    @Id
    @Column(name = "category_name",unique = true,nullable = false)
    private String categoryName;

    @Column(nullable = false,name = "category_priority", unique = true)
    private int categoryPriority;

    @OneToMany(mappedBy = "category",fetch = FetchType.EAGER)
    private Set<Menu> menuList=new HashSet<>();
}
