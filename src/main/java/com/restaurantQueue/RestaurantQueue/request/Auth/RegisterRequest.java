package com.restaurantQueue.RestaurantQueue.request.Auth;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String email;
    private String password;
    private String phoneNumber;
    private String roles = "USER";
}
