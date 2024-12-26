package com.restaurantQueue.RestaurantQueue.config;

import com.restaurantQueue.RestaurantQueue.exceptions.AppException;
import com.restaurantQueue.RestaurantQueue.exceptions.AuthenticationException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

@Component
public class ApiKeyFilter extends OncePerRequestFilter {


    @Value("${api.key}")
    private String API_KEY;

    @Value("${api.secret}")
    private String API_SECRET;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String apiKey = request.getHeader("API-KEY");
        String apiSecret = request.getHeader("API-SECRET");;

        if(Objects.equals(apiKey, API_KEY) && Objects.equals(apiSecret, API_SECRET)){

        }else{
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // Write JSON error message
            String jsonError = "{ \"error\": \"wrong api key or secret\" }";
            response.getWriter().write(jsonError);

            return;
        }

        filterChain.doFilter(request, response);
    }
}
