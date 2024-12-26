package com.restaurantQueue.RestaurantQueue.service.Auth;

import com.restaurantQueue.RestaurantQueue.dto.request.Auth.LoginRequest;
import com.restaurantQueue.RestaurantQueue.dto.request.Auth.RegisterRequest;
import com.restaurantQueue.RestaurantQueue.dto.response.ResponseWrapper;
import com.restaurantQueue.RestaurantQueue.exceptions.AuthenticationException;
import com.restaurantQueue.RestaurantQueue.exceptions.UserAlreadyExistException;
import com.restaurantQueue.RestaurantQueue.exceptions.UserNotFoundException;
import com.restaurantQueue.RestaurantQueue.helper.EmailValidator;
import com.restaurantQueue.RestaurantQueue.helper.PasswordValidator;
import com.restaurantQueue.RestaurantQueue.model.User.User;
import com.restaurantQueue.RestaurantQueue.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

  private BCryptPasswordEncoder bCryptPasswordEncoder;

  @Autowired private EmailValidator emailValidator;

  @Autowired private PasswordValidator passwordValidator;

  @Autowired private UserRepository userRepository;

  @Autowired private JwtService jwtService;

  @Autowired AuthenticationManager authenticationManager;

  public AuthServiceImpl() {
    bCryptPasswordEncoder = new BCryptPasswordEncoder(12);
  }

  @Override
  public ResponseWrapper<User> register(RegisterRequest registerRequest) {

    // check the register parameter  and validation of email and password
    checkEmailAndPassword(registerRequest.getEmail(), registerRequest.getPassword());

    // check the email is already register or not
    if (userRepository.existsByEmail(registerRequest.getEmail())) {
      throw new UserAlreadyExistException("User already registered by this email!");
    }

    // register the user
    User user = new User();
    user.setUsername(registerRequest.getUsername());
    user.setEmail(registerRequest.getEmail());
    user.setPhoneNumber(registerRequest.getPhoneNumber());
    user.setPassword(bCryptPasswordEncoder.encode(registerRequest.getPassword()));
    user.setRoles(registerRequest.getRoles());

    userRepository.save(user);

    return retrieveUserSetTokenAndRetrieve(registerRequest.getEmail());
  }

  @Override
  public ResponseWrapper<User> login(LoginRequest loginRequest) {

    // check email and password validation
    checkEmailAndPassword(loginRequest.getEmail(), loginRequest.getPassword());

    // check the user is exit or not by email
    userRepository
        .findByEmail(loginRequest.getEmail())
        .orElseThrow(
            () -> new UserNotFoundException("Customer not found!,Please check your email"));
    System.out.println("authenicate== ");

    // check the user is authenicated or not
    try {
      Authentication authentication =
          authenticationManager.authenticate(
              new UsernamePasswordAuthenticationToken(
                  loginRequest.getEmail(), loginRequest.getPassword()));
      if (authentication.isAuthenticated()) {
        return retrieveUserSetTokenAndRetrieve(loginRequest.getEmail());
      } else {
        throw new AuthenticationException("Invalid email or password");
      }
    } catch (RuntimeException e) {
      throw new AuthenticationException("Invalid email or password");
    }
  }

  private void checkEmailAndPassword(String email, String password) {
    emailValidator.validate(email);
    passwordValidator.validate(password);
  }

  private ResponseWrapper<User> retrieveUserSetTokenAndRetrieve(String email) {

    String token = jwtService.generateToken(email);

    User responseUser =
        userRepository
            .findByEmail(email)
            .orElseThrow(
                () -> new UserNotFoundException("Customer not found!,Please check your email"));

    responseUser.setToken(token);
    ResponseWrapper<User> responseWrapper = new ResponseWrapper<>();
    responseWrapper.setSuccess(true);
    responseWrapper.setResult(responseUser);
    return responseWrapper;
  }
}
