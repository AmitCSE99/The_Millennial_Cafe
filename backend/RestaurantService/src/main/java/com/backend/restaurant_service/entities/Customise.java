package com.backend.restaurant_service.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
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
    @Column(name = "id")
    private String customiseId;

    @Column(name = "name",unique = true)
    @Length(min = 5)
    private String customiseName;

    @Column(name = "description")
    @Length(min = 5)
    private String customiseDescription;

    @Column(name = "price")
    @Min(10)
    @Max(200)
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
