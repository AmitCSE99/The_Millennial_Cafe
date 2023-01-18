package com.backend.user_service.repositories;

import com.backend.user_service.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,String> {

    List<User> findByUserEmailAndUserPassword(String username,String password);
}
