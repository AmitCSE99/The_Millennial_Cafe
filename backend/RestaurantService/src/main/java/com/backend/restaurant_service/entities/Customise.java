package com.backend.restaurant_service.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
@ToString
public class Customise {

    @Id
    @Column(name = "customise_id")
    private String customiseId;

    @Column(name = "customise_name",unique = true)
    private String customiseName;

    @Column(name = "customise_description")
    private String customiseDescription;

    @Column(name = "customise_price")
    private int customisePrice;

    @ManyToMany(mappedBy = "customisations",fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<Menu> menuList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Customise)) return false;
        Customise customise = (Customise) o;
        return Objects.equals(customiseId, customise.customiseId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(customiseId);
    }
}
