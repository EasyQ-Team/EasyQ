package com.restaurantQueue.RestaurantQueue.service.Auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Service
public class JwtService {
    private static final String SECREAT = "5AqLZtiNvoPVyOvHQ6ZDjY+5TlU+9TpQ0vZZzV3sm7o=";

    public String generateToken(String email) {
        long currentTimeMillis = System.currentTimeMillis();
        long expirationTimeMillis = currentTimeMillis + 3600000;

        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date(currentTimeMillis))
                .setExpiration(new Date(expirationTimeMillis))
                .signWith(getKey(), SignatureAlgorithm.HS256) // Sign the token with the secret key
                .compact();
    }

    public Key getKey() {
        byte[] keyByte = Decoders.BASE64.decode(SECREAT);
        return Keys.hmacShaKeyFor(keyByte);
    }

    public String extractName(String token) {
        return extractName(token, Claims::getSubject);
    }

    private <T>T extractClaim(String token, Function<Claims,T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private <T> T extractName(String token, Function<Claims,T> claimsTFunction) {
        final Claims claims = extractAllClaims(token);
        return claimsTFunction.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return (Claims) Jwts.parserBuilder().setSigningKey(getKey()).build().parseClaimsJws(token).getBody();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        final String userName = extractName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}