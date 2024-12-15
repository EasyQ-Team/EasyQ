package com.restaurantQueue.RestaurantQueue.exceptions;

import com.restaurantQueue.RestaurantQueue.dto.response.ErrorResponse;
import com.restaurantQueue.RestaurantQueue.helper.Validator;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice public class GlobalExceptionHandler {


    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(UserNotFoundException e){


        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body( ErrorResponse.builder()
                        .setStatusCode(HttpStatus.NOT_FOUND.value())
                        .setTitle("User Not Found")
                        .setMessage(e.getMessage())
                        .build());
    }

    @ExceptionHandler(ValidatorException.class)
    public ResponseEntity<ErrorResponse> handleUserNotFoundException(ValidatorException e){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body( ErrorResponse.builder()
                        .setStatusCode(HttpStatus.BAD_REQUEST.value())
                        .setTitle("Bad Request")
                        .setMessage(e.getMessage())
                        .build());
    }


}
