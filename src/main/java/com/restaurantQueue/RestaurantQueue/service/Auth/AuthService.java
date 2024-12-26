package com.restaurantQueue.RestaurantQueue.service.Auth;

import com.restaurantQueue.RestaurantQueue.dto.request.Auth.LoginRequest;
import com.restaurantQueue.RestaurantQueue.dto.request.Auth.RegisterRequest;
import com.restaurantQueue.RestaurantQueue.dto.response.ResponseWrapper;
import com.restaurantQueue.RestaurantQueue.model.User.User;

public interface AuthService {


    ResponseWrapper<User> register(RegisterRequest registerRequest);


    ResponseWrapper<User> login(LoginRequest loginRequest);

}
