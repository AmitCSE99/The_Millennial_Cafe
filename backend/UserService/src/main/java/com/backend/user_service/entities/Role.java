package com.backend.user_service.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "roles")
public class Role {

    @Id
    private String roleName;
    private String roleDescription;

    @ManyToMany(mappedBy = "roles",fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<User> users;
}
