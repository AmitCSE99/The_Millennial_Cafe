package com.backend.user_service.services.impl;

import com.backend.user_service.entities.Address;
import com.backend.user_service.entities.User;
import com.backend.user_service.repositories.AddressRepository;
import com.backend.user_service.repositories.UserRepository;
import com.backend.user_service.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AddressImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Address createAddress(Address address) {
        String addressId= UUID.randomUUID().toString();
        address.setAddressId(addressId);
        return addressRepository.save(address);
    }

    @Override
    public List<Address> getAddressByUserId(String userId) {
        System.out.println(userId);
        User user= userRepository.findById(userId).orElseThrow(()->new RuntimeException("no user"));
        return addressRepository.findByUser(user);
    }

    @Override
    public Address editAddress(Address address) {
        return null;
    }

    @Override
    public Address deleteAddress(String addressId) {
        return null;
    }
}
