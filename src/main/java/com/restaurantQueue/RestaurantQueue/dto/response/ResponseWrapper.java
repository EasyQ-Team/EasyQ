package com.restaurantQueue.RestaurantQueue.dto.response;

import lombok.Data;

@Data
public class ResponseWrapper<T> {

    private  T result;
    private  boolean success;
    private  ErrorResponse error;

}
