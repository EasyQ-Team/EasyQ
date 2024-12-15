package com.restaurantQueue.RestaurantQueue.helper;

import com.google.api.pathtemplate.ValidationException;
import com.restaurantQueue.RestaurantQueue.exceptions.ValidatorException;

public class PasswordValidator implements  Validator<String>{

    private static final int PASSWORD_MIN_LENGTH = 8;

    @Override
    public void validate(String password) throws ValidatorException {
        if (password == null || password.length() < PASSWORD_MIN_LENGTH) {
            throw new ValidationException("Please enter at least 8 letter!");
        }
    }
}
