package com.restaurantQueue.RestaurantQueue.helper;

import com.restaurantQueue.RestaurantQueue.exceptions.ValidatorException;
import org.springframework.stereotype.Component;

@Component
public class PasswordValidator implements  Validator<String>{

    private static final int PASSWORD_MIN_LENGTH = 8;

    @Override
    public void validate(String password) throws ValidatorException {
        if (password == null || password.length() < PASSWORD_MIN_LENGTH) {
            throw new ValidatorException("Please enter at least 8 letter!");
        }
    }
}
