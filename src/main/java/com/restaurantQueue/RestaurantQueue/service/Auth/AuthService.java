package com.restaurantQueue.RestaurantQueue.service.Auth;

import com.restaurantQueue.RestaurantQueue.dto.request.Auth.LoginRequest;
import com.restaurantQueue.RestaurantQueue.dto.request.Auth.RegisterRequest;
import com.restaurantQueue.RestaurantQueue.model.User.User;


import java.util.Optional;

public interface AuthService {


    User register(RegisterRequest registerRequest);


    Optional<User> login(LoginRequest loginRequest);

}
