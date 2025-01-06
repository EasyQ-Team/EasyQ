package com.restaurantQueue.RestaurantQueue.controller.Notification;

import com.restaurantQueue.RestaurantQueue.request.Notification.NotificationRequest;
import com.restaurantQueue.RestaurantQueue.request.Notification.NotificationResponse;
import com.restaurantQueue.RestaurantQueue.service.Auth.FCMService;
import com.restaurantQueue.RestaurantQueue.service.Notification.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("notification")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @PostMapping("/send")
    public ResponseEntity<String> sendNotification(@RequestBody NotificationRequest request) throws ExecutionException, InterruptedException {
        notificationService.sendPushNotificationToToken(request);
        return new ResponseEntity<>("Send Successfully", HttpStatus.OK);
    }
}
