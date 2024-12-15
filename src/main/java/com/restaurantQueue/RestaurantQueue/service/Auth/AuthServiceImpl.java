package com.restaurantQueue.RestaurantQueue.service.Auth;

import com.restaurantQueue.RestaurantQueue.dto.request.Auth.LoginRequest;
import com.restaurantQueue.RestaurantQueue.dto.request.Auth.RegisterRequest;
import com.restaurantQueue.RestaurantQueue.exceptions.UserAlreadyExistException;
import com.restaurantQueue.RestaurantQueue.exceptions.UserNotFoundException;
import com.restaurantQueue.RestaurantQueue.helper.EmailValidator;
import com.restaurantQueue.RestaurantQueue.helper.PasswordValidator;
import com.restaurantQueue.RestaurantQueue.model.User.User;
import com.restaurantQueue.RestaurantQueue.repository.UserRepository;
import com.restaurantQueue.RestaurantQueue.service.User.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService{

    private final EmailValidator emailValidator;
    private final PasswordValidator passwordValidator;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JwtService jwtService;

    @Autowired
    UserService userService;

    public AuthServiceImpl() {

        emailValidator =new EmailValidator();
        passwordValidator =new PasswordValidator();
        bCryptPasswordEncoder =new BCryptPasswordEncoder(12);
    }



    @Override
    public User register(RegisterRequest registerRequest) {

        //check the register parameter  and validation of email and password
        emailValidator.validate(registerRequest.getEmail());
        passwordValidator.validate(registerRequest.getPassword());

        //check the email is already register or not
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new UserAlreadyExistException("User already registered by this email!");
        }

        //register the user
        userService.saveUser(registerRequest);

        //fetch the user generate token and return response
        User responseUser = userRepository.findByEmail(registerRequest.getEmail()).orElseThrow(
                ()-> new UserNotFoundException("Something went wrong!")
        );

        var token = jwtService.generateToken(registerRequest.getEmail());
        responseUser.setToken(token);

        return responseUser;
    }

    @Override
    public Optional<User> login(LoginRequest loginRequest) {

        //check email and password validation


        //check the user is authenicated or not


        //return the user with token

        return Optional.empty();


    }
}
