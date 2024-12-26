//package com.restaurantQueue.RestaurantQueue.service.Auth;
//
//import com.google.firebase.messaging.*;
//import com.google.gson.Gson;
//import com.google.gson.GsonBuilder;
//import com.restaurantQueue.RestaurantQueue.dto.request.Notification.NotificationRequest;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Service;
//
//import java.time.Duration;
//import java.util.concurrent.ExecutionException;
//
//@Service
//public class FCMService {
//    private Logger logger = LoggerFactory.getLogger(FCMService.class);
//
//    public void sendMessageToToken(NotificationRequest request)
//            throws InterruptedException, ExecutionException {
//        Message message = getPreconfiguredMessageToToken(request);
//        Gson gson = new GsonBuilder().setPrettyPrinting().create();
//        String jsonOutput = gson.toJson(message);
//        String response = sendAndGetResponse(message);
//        logger.info("Sent message to token. Device token: " + request.getToken() + ", " + response + " msg " + jsonOutput);
//    }
//
//    private String sendAndGetResponse(Message message) throws InterruptedException, ExecutionException {
//        return FirebaseMessaging.getInstance().sendAsync(message).get();
//    }
//
//    private AndroidConfig getAndroidConfig(String topic) {
//        return AndroidConfig.builder()
//                .setTtl(Duration.ofMinutes(2).toMillis())
//                .setCollapseKey(topic)
//                .setPriority(AndroidConfig.Priority.HIGH)
//                .setNotification(AndroidNotification.builder()
//                        .setTag(topic).build())
//                .build();
//    }
//
//    private ApnsConfig getApnsConfig(String topic) {
//        return ApnsConfig.builder()
//                .setAps(Aps.builder().setCategory(topic).setThreadId(topic).build())
//                .build();
//    }
//
//    // No need to set the token here again since it's done in the builder.
//    private Message getPreconfiguredMessageToToken(NotificationRequest request) {
//        return getPreconfiguredMessageBuilder(request).build();
//    }
//
//    private Message.Builder getPreconfiguredMessageBuilder(NotificationRequest request) {
//        AndroidConfig androidConfig = getAndroidConfig(request.getTopic());
//        ApnsConfig apnsConfig = getApnsConfig(request.getTopic());
//        Notification notification = Notification.builder()
//                .setTitle(request.getTitle())
//                .setBody(request.getBody())
//                .build();
//
//        // Set the token directly in the builder here.
//        return Message.builder()
//                .setToken(request.getToken())  // Set the token correctly during message construction
//                .setApnsConfig(apnsConfig)
//                .setAndroidConfig(androidConfig)
//                .setNotification(notification);
//    }
//}
