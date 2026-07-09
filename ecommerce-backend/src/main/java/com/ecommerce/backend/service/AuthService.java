package com.ecommerce.backend.service;

import com.ecommerce.backend.dto.LoginRequest;
import com.ecommerce.backend.dto.RegisterRequest;
import com.ecommerce.backend.entity.User;
import com.ecommerce.backend.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthService {


    private final UserRepository userRepository;


    public AuthService(UserRepository userRepository) {

        this.userRepository = userRepository;

    }



    // REGISTER
    public String register(RegisterRequest request) {


        if(userRepository.findByEmail(request.getEmail()).isPresent()){

            return "User already exists";

        }


        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        // simple password storage temporarily
        user.setPassword(request.getPassword());


        userRepository.save(user);


        return "User registered successfully";

    }




    // LOGIN
    public User login(LoginRequest request){

        System.out.println("EMAIL RECEIVED = " + request.getEmail());
        System.out.println("PASSWORD RECEIVED = " + request.getPassword());


        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new RuntimeException("Invalid email")
                );


        System.out.println("DB PASSWORD = " + user.getPassword());


        if(!user.getPassword().equals(request.getPassword())){

            throw new RuntimeException("Invalid password");

        }


        return user;
    }

}