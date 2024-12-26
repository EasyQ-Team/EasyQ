package com.restaurantQueue.RestaurantQueue.helper;

import com.restaurantQueue.RestaurantQueue.exceptions.ValidatorException;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class EmailValidator implements Validator<String>{

    private static final String EMAIL_REGEX = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";
    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);


    @Override
    public void validate(String email) throws ValidatorException {
        if(email == null || email.isEmpty() ||!EMAIL_PATTERN.matcher(email).matches() ){
            throw new ValidatorException("Please enter a valid email");
        }
    }
}
