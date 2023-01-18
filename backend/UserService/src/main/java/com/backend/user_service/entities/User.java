package com.backend.user_service.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Pattern;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
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
    private List<Address> address;
}
