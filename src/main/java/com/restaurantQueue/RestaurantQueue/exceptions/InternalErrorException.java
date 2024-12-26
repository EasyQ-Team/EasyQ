package com.restaurantQueue.RestaurantQueue.exceptions;

public class InternalErrorException extends AppException{
    public InternalErrorException(String message, String title, int statusCode) {
        super(message, title, statusCode);
    }
}
