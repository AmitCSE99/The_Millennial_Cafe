package com.backend.user_service.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Entity
@Table(name = "addresses")
public class Address {

    @Id
    @Column(name = "ID")
    private String addressId;

    @Column(name = "LATITUDE",nullable = false)
    private double addressLatitude;

    @Column(name = "LONGITUDE",nullable = false)
    private double addressLongitude;

    @Column(name = "FULL_ADDRESS",nullable = false)
    private String addressFullAddress;

    @Column(name = "LANDMARK",nullable = false)
    private String addressLandmark;

    @Column(name = "PIN_CODE",nullable = false)
    private int addressPinCode;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;
}
