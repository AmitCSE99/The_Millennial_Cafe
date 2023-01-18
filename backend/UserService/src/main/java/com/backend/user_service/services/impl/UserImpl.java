package com.backend.user_service.services.impl;

import com.backend.user_service.entities.User;
import com.backend.user_service.exceptions.InvalidDataException;
import com.backend.user_service.exceptions.ResourceNotFoundException;
import com.backend.user_service.repositories.UserRepository;
import com.backend.user_service.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class UserImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        String userId= UUID.randomUUID().toString();
        user.setUserId(userId);
        return userRepository.save(user);
    }

    @Override
    public User getUserById(String userId) {
        return userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User is not found!"));
    }

    @Override
    public User loginUser(Map<String, String> userData) {
        if(!userData.containsKey("userEmail")||!userData.containsKey("userPassword")){
            throw new InvalidDataException("Invalid Data");
        }
        List<User> user=userRepository.findByUserEmailAndUserPassword(userData.get("userEmail"),userData.get("userPassword"));
        if(user.isEmpty()){
            throw new InvalidDataException("Either email or Password is wrong!");
        }
        return user.get(0);
    }

    @Override
    public User editUser(User user) {
        return null;
    }

    @Override
    public User deleteUser(String userId) {
        return null;
    }
}
