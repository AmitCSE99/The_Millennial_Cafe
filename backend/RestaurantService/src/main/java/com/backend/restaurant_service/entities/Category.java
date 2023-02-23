package com.backend.restaurant_service.entities;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
    @Column(name = "name",unique = true,nullable = false)
    @Length(min = 5)
    private String categoryName;

    @Column(nullable = false,name = "priority", unique = true)
    @Min(0)
    @Max(6)
    private int categoryPriority;

    @OneToMany(mappedBy = "category",fetch = FetchType.EAGER)
    private Set<Menu> menuList=new HashSet<>();
}
