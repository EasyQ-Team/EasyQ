package com.restaurantQueue.RestaurantQueue.config;

import java.security.SecureRandom;
import java.util.Base64;

public class SecureKeyGenerator {
    public static void main(String[] args) {
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[32];
        secureRandom.nextBytes(key);

        String base64EncodedKey = Base64.getEncoder().encodeToString(key);

        System.out.println("Generated Secret Key: " + base64EncodedKey);
    }
}
