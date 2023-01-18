package com.backend.user_service.repositories;

import com.backend.user_service.entities.Address;
import com.backend.user_service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,String> {

    public List<Address> findByUser(User user);
}
