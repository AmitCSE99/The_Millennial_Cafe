package com.backend.restaurant_service.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
    @Column(name = "id")
    private String menuId;

    @Column(name = "name",unique = true)
    @Length(min = 5)
    private String menuName;

    @Column(name = "description")
    @Length(min = 5)
    private String menuDescription;

    @Column(name="price")
    @Min(10)
    private int menuPrice;

    @Column(name = "image_url")
    private String menuImageUrl;

    @Column(name = "discount",nullable = false)
    @Min(0)
    @Max(40)
    private int menuDiscount;

    @Column(name = "is_Recommended",columnDefinition = "boolean default false",nullable = false)
    private boolean menuIsRecommended;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category")
    @JsonBackReference
    private Category category;

    @ManyToMany(fetch = FetchType.EAGER,targetEntity = Customise.class)
    @JoinTable(name = "menu_customise",joinColumns = {@JoinColumn(name = "menu_id")},inverseJoinColumns = {
            @JoinColumn(name = "customise_id")
    })
    private List<Customise> customisations=new ArrayList<>();
}
