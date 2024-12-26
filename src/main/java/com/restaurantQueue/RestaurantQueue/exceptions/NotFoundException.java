package com.restaurantQueue.RestaurantQueue.exceptions;

public class NotFoundException extends AppException {

    public NotFoundException(String message, String title, int statusCode) {
        super(message, title, statusCode);
    }
}
