package com.restaurantQueue.RestaurantQueue.dto.request.Auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String email;
    private String password;
}
