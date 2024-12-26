package com.restaurantQueue.RestaurantQueue.exceptions;

import com.restaurantQueue.RestaurantQueue.dto.response.ErrorResponse;
import com.restaurantQueue.RestaurantQueue.dto.response.ResponseWrapper;
import com.restaurantQueue.RestaurantQueue.helper.Validator;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler({UserAlreadyExistException.class})
  public ResponseEntity<ResponseWrapper<ErrorResponse>> handleUserAlreadyExitException(
      UserAlreadyExistException e) {

    var response =
        getErrorResponse(HttpStatus.FORBIDDEN.value(), "User Already Existed", e.getMessage());

    return ResponseEntity.status(HttpStatus.FORBIDDEN).body(response);
  }

  @ExceptionHandler({UserNotFoundException.class})
  public ResponseEntity<ResponseWrapper<ErrorResponse>> handleUserNotFoundException(
      UserNotFoundException e) {

    var response = getErrorResponse(HttpStatus.NOT_FOUND.value(), "User Not Found", e.getMessage());

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
  }

  @ExceptionHandler({ValidatorException.class})
  public ResponseEntity<ResponseWrapper<ErrorResponse>> handleValidatorException(
      ValidatorException e) {

    var response = getErrorResponse(HttpStatus.BAD_REQUEST.value(), "Bad Request", e.getMessage());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ExceptionHandler({AuthenticationException.class})
  public ResponseEntity<ResponseWrapper<ErrorResponse>> handleAuthenticationException(
      AuthenticationException e) {

    var response = getErrorResponse(HttpStatus.FORBIDDEN.value(), "Forbidden", e.getMessage());

    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
  }

  @ExceptionHandler({AppException.class})
  public ResponseEntity<ResponseWrapper<ErrorResponse>> handleAppException(
          AppException e) {

    var response = getErrorResponse(e.getStatusCode(), e.getTitle(), e.getMessage());

    return ResponseEntity.status(e.getStatusCode()).body(response);
  }

  @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
  public ResponseEntity<ResponseWrapper<ErrorResponse>> handleRequestMethodNotSupportException(
      HttpRequestMethodNotSupportedException e) {

    var response =
        getErrorResponse(
            HttpStatus.METHOD_NOT_ALLOWED.value(), "Method Not Allowed", e.getMessage());

    return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(response);
  }

  private ResponseWrapper<ErrorResponse> getErrorResponse(
      int statusCode, String title, String message) {

    ResponseWrapper<ErrorResponse> response = new ResponseWrapper<>();
    response.setSuccess(false);
    response.setError(
        ErrorResponse.builder()
            .setStatusCode(statusCode)
            .setTitle(title)
            .setMessage(message)
            .build());

    return response;
  }
}
