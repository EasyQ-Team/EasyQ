package com.restaurantQueue.RestaurantQueue.controller.User;

import com.restaurantQueue.RestaurantQueue.dto.request.Auth.LoginRequest;
import com.restaurantQueue.RestaurantQueue.dto.request.Auth.RegisterRequest;
import com.restaurantQueue.RestaurantQueue.dto.response.ResponseWrapper;
import com.restaurantQueue.RestaurantQueue.model.User.User;
import com.restaurantQueue.RestaurantQueue.service.Auth.AuthService;
import com.restaurantQueue.RestaurantQueue.service.Auth.JwtService;
import com.restaurantQueue.RestaurantQueue.service.Auth.SSOLogin;
import com.restaurantQueue.RestaurantQueue.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
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
    private AuthService authService;

    @Autowired
    private JwtService jwtService;


    @Autowired
    @Qualifier("google-login")
    private SSOLogin googleLogin;


    @Autowired
    AuthenticationManager authenticationManager;

    @PostMapping("/register")
    public ResponseEntity<ResponseWrapper<User>> register(@RequestBody RegisterRequest registerRequest) {
        var response = authService.register(registerRequest);
        return new ResponseEntity<ResponseWrapper<User>>(response, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseWrapper<User>> login(@RequestBody LoginRequest loginRequest) {

        var response = authService.login(loginRequest);
        return new ResponseEntity<ResponseWrapper<User>>(response, HttpStatus.OK);

    }
}
