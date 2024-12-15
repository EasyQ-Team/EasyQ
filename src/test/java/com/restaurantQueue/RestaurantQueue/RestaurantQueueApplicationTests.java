package com.restaurantQueue.RestaurantQueue;

import com.restaurantQueue.RestaurantQueue.dto.request.Auth.RegisterRequest;
import com.restaurantQueue.RestaurantQueue.service.Auth.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RestaurantQueueApplicationTests {


	@Autowired
	private AuthService authService;

	@Test
	void contextLoads() {
	}

	@Test
	void testRegister(RegisterRequest request){
		var user = authService.register(request);


	}

}
