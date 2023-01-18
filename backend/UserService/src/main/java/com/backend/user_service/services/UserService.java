package com.backend.user_service.services;

import com.backend.user_service.entities.User;
import com.backend.user_service.exceptions.InvalidDataException;

import java.util.Map;

public interface UserService {

    User createUser(User user);

    User getUserById(String userId);

    User loginUser(Map<String,String> userData);

    User editUser(User user);

    User deleteUser(String userId);
}
