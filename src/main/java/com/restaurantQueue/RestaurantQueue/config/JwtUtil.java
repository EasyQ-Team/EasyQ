package com.restaurantQueue.RestaurantQueue.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Base64;
import java.util.Date;

@Component
public class JwtUtil {
    private final String SECRET_KEY = generateSecureKey();
    private final int TOKEN_VALIDITY = 1000 * 60 * 60 * 1; // 10 hours

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + TOKEN_VALIDITY))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public String extractUsername(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean isTokenValid(String token, String username) {
        return extractUsername(token).equals(username) && !isTokenExpired(token);
    }

    private boolean isTokenExpired(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody().getExpiration().before(new Date());
    }

    private String generateSecureKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] key = new byte[32];  // 32 bytes = 256 bits
        secureRandom.nextBytes(key);

        // Base64 encode the key to store and use it in JWT signing
        return Base64.getEncoder().encodeToString(key);
    }
}