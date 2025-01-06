package com.restaurantQueue.RestaurantQueue.service.Notification;

import com.restaurantQueue.RestaurantQueue.request.Notification.NotificationRequest;
import com.restaurantQueue.RestaurantQueue.service.Auth.FCMService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutionException;

@Service
public class NotificationService {
    private Logger logger = LoggerFactory.getLogger(NotificationService.class);
    private FCMService fcmService;

    public NotificationService(FCMService fcmService) {
        this.fcmService = fcmService;
    }

    public void sendPushNotificationToToken(NotificationRequest notificationRequest) {
        try {
            fcmService.sendMessageToToken(notificationRequest);
        } catch (Error | InterruptedException | ExecutionException error) {
            System.out.println(error);
        }
    }
}


