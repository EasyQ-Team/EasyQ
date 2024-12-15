package com.restaurantQueue.RestaurantQueue.helper;

import com.restaurantQueue.RestaurantQueue.exceptions.ValidatorException;

public interface Validator<T> {

    void validate(T input ) throws ValidatorException;

    void validate(String input) throws ValidatorException;
}
