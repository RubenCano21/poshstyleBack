package com.andres.curso.springboot.app.springbootcrud.services;

import com.andres.curso.springboot.app.springbootcrud.security.filter.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private AuthenticationManager authenticationManager;

//    @Autowired
//    private JwtAuthenticationFilter jwtAuthenticationFilter;

    //public String authenticateUser()
}
