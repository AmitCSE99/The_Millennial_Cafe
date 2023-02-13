package com.backend.user_service.services.impl;

import com.backend.user_service.entities.Role;
import com.backend.user_service.entities.User;
import com.backend.user_service.exceptions.InvalidDataException;
import com.backend.user_service.exceptions.ResourceNotFoundException;
import com.backend.user_service.repositories.RoleRepository;
import com.backend.user_service.repositories.UserRepository;
import com.backend.user_service.services.UserService;
import com.backend.user_service.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTUtils jwtUtils;

    @Override
    public User createUser(User user) {
        String userId= UUID.randomUUID().toString();
        user.setUserId(userId);
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        Role role=roleRepository.findById("admin").orElseThrow(()->new ResourceNotFoundException("Not found"));
        Set<Role> roles=new HashSet<>();
        roles.add(role);
        user.setRoles(roles);
        System.out.println(user);
        System.out.println(user.getRoles());
        return userRepository.save(user);
    }

    @Override
    public User getUserById(String userId) throws ResourceNotFoundException {
        return userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User is not found!"));
    }

    @Override
    public Map<String,Object> loginUser(Map<String, String> userData) {
        if(!userData.containsKey("userEmail")||!userData.containsKey("userPassword")){
            throw new InvalidDataException("Insufficient Data");
        }
        Map<String,Object> response=new HashMap<>();
        List<User> userList=userRepository.findByUserEmail(userData.get("userEmail"));
        if(userList.isEmpty()){
            throw new InvalidDataException("Your Email ID is wrong!");
        }
        User user=userList.get(0);
        if(!passwordEncoder.matches(userData.get("userPassword"),user.getUserPassword())){
            throw new InvalidDataException(("Password is wrong!"));
        }
        UserDetails userDetails=new org.springframework.security.core.userdetails.User(user.getUserEmail(),user.getUserPassword(),getAuthorities(user));
        String token= jwtUtils.generateToken(userDetails);
        response.put("user",user);
        response.put("accessToken",token);
        return response;
    }

    @Override
    public User editUser(User user) {
        if(user.getUserId()==null){
            throw new InvalidDataException("Invalid Data!");
        }
        User checkUser=userRepository.findById(user.getUserId()).orElseThrow(()->new ResourceNotFoundException("No such user available"));
        user.setUserPassword(checkUser.getUserPassword());
        return userRepository.save(user);
    }

    @Override
    public void deleteUser(String userId) {
        userRepository.deleteById(userId);
    }

    private Set<SimpleGrantedAuthority> getAuthorities(User user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        user.getRoles().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        });
        return authorities;
    }
}
