package com.restaurantQueue.RestaurantQueue.exceptions;


public class AppException extends RuntimeException{

    private final String title;
    private final int statusCode;

    public AppException(String message, String title, int statusCode) {
        super(message);
        this.title = title;
        this.statusCode = statusCode;
    }

    public String getTitle() {
        return title;
    }

    public int getStatusCode() {
        return statusCode;
    }
}

