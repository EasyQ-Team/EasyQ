package com.restaurantQueue.RestaurantQueue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class RestaurantQueueApplication {
	public static void main(String[] args) {
		SpringApplication.run(RestaurantQueueApplication.class, args);
	}
}
