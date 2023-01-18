package com.backend.user_service.services;

import com.backend.user_service.entities.Address;

import java.util.List;

public interface AddressService {

    Address createAddress(Address address);

    List<Address> getAddressByUserId(String userId);

    Address editAddress(Address address);

    Address deleteAddress(String addressId);
}
