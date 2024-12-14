package com.restaurantQueue.RestaurantQueue.controller.User;

import com.restaurantQueue.RestaurantQueue.request.Auth.LoginRequest;
import com.restaurantQueue.RestaurantQueue.request.Auth.RegisterRequest;
import com.restaurantQueue.RestaurantQueue.service.Auth.JwtService;
import com.restaurantQueue.RestaurantQueue.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
        if (registerRequest.getUsername() == null || registerRequest.getPassword() == null) {
            throw new IllegalArgumentException("Username and password must not be null");
        }
        userService.saveUser(registerRequest);
        return new ResponseEntity<>("Register successfully", HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(loginRequest.getEmail());
        } else {
            return "Failed";
        }
    }
}
