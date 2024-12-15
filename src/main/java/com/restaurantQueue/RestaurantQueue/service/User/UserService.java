package com.restaurantQueue.RestaurantQueue.service.User;

import com.restaurantQueue.RestaurantQueue.model.User.User;
import com.restaurantQueue.RestaurantQueue.repository.UserRepository;
import com.restaurantQueue.RestaurantQueue.dto.request.Auth.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(12);

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
