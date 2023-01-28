package com.backend.api_gateway.controllers;

import com.backend.api_gateway.entities.AuthResponse;
import com.backend.api_gateway.entities.User;
import com.backend.api_gateway.external_services.UserService;
import com.backend.api_gateway.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@RestController
public class SampleController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtils jwtUtils;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private AuthResponse userDetails(Map<String,Object> data){

        LinkedHashMap<?,?> map= (LinkedHashMap<?, ?>) data.get("data");

        AuthResponse response=new AuthResponse();
        response.setUserEmail((String)map.get("userEmail"));
        response.setUserPassword((String)map.get("userPassword"));
        List<LinkedHashMap<String,String>> roleList= (List<LinkedHashMap<String, String>>) map.get("roles");
        //Set<Role> roles=new HashSet<>(roleList);
        //response.setRoles(roles);
//        System.out.println(response);
//        roleList.forEach(role -> {
//            System.out.println(role);
//        });
        System.out.println(getAuthorities(roleList));


        return response;
    }

    private Set<SimpleGrantedAuthority> getAuthorities(List<LinkedHashMap<String,String>> roles) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();
        roles.forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.get("roleName")));
//            System.out.println(role.getClass());
        });
        return authorities;
    }

    @PostMapping("/create")
    public ResponseEntity<Mono<Map<String,Object>>> createUser(@RequestBody User user){
        System.out.println(passwordEncoder.encode(user.getUserPassword()));
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        return ResponseEntity.ok().body(userService.createUser(user));
    }

    @PostMapping("/sample")
    public ResponseEntity<Map<String,Object>> getSampleUser(@RequestBody Map<String,String> userData){
        String userId=userData.get("userId");
        String password=userData.get("password");
        Mono<Map<String,Object>> user=userService.getUser(userId);
        AtomicReference<String> jwt= new AtomicReference<>("");
        user.subscribe(data->{
            LinkedHashMap<?,?> map= (LinkedHashMap<?, ?>) data.get("data");
            //System.out.println(userDetails(data));
            String username=(String)map.get("userId");
            String userPassword=(String)map.get("userPassword");
            List<LinkedHashMap<String,String>> roleList= (List<LinkedHashMap<String, String>>) map.get("roles");
            UserDetails userDetails= new org.springframework.security.core.userdetails.User(username,userPassword,getAuthorities(roleList));
            System.out.println(jwtUtils.generateToken(userDetails));
            jwt.set(jwt + jwtUtils.generateToken(userDetails));
            data.put("jwtToken",jwt);
            Set<SimpleGrantedAuthority> authorities=getAuthorities(roleList);
        });
        System.out.println(jwt);
        //Map<String,Object> data2=userService.getUser(userId).block();
       return ResponseEntity.ok().body(new HashMap<>());
    }
}
