package com.restaurantQueue.RestaurantQueue.dto.request.Notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class NotificationResponse {
    private int status;
    private String message;
}
