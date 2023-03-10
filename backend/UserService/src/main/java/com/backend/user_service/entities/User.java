package com.backend.user_service.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.Objects;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
@Entity
@Table(name = "users")
public class User {

    @Id
    @Column(name = "ID")
    private String userId;

    @Column(name = "NAME" ,unique = true,nullable = false,length = 25)
    private String username;

    @Column(name = "FIRST_NAME",length = 30,nullable = false)
    private String firstName;

    @Column(name="LAST_NAME",length = 30)
    private String lastName;

    @Column(name="EMAIL",unique = true,nullable = false)
    @Email(regexp = "[a-z0-9._%+-]+@[a-z0-9.-]+\\.[a-z]{2,3}",
            flags = Pattern.Flag.CASE_INSENSITIVE)
    private String userEmail;

    @Column(name = "PASSWORD",nullable = false)
    private String userPassword;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER)
    @JsonManagedReference
    private Set<Address> userAddress;

    @OneToMany(mappedBy = "user",fetch =FetchType.EAGER)
    @JsonManagedReference
    private Set<Cart> userCartItems;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonManagedReference
    @JsonIgnore
    @JoinTable(name = "USER_ROLE", joinColumns = { @JoinColumn(name = "USER_ID") }, inverseJoinColumns = {
            @JoinColumn(name = "ROLE_ID") })
    private Set<Role> roles;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return Objects.equals(userId, user.userId) && Objects.equals(username, user.username) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(userEmail, user.userEmail) && Objects.equals(userPassword, user.userPassword) && Objects.equals(userAddress, user.userAddress) && Objects.equals(userCartItems, user.userCartItems) && Objects.equals(roles, user.roles);
    }

}
