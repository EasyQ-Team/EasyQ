package com.restaurantQueue.RestaurantQueue.controller.Queue;

import com.restaurantQueue.RestaurantQueue.helper.QueueStatus;
import com.restaurantQueue.RestaurantQueue.model.Queue.Queue;
import com.restaurantQueue.RestaurantQueue.service.Queue.QueueService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("queue")
public class QueueController {
    private final QueueService queueService;

    public QueueController(QueueService queueService) {
        this.queueService = queueService;
    }

    @PostMapping("/join")
    public ResponseEntity<String> join(Authentication authentication) {
        String email = ((UserDetails) authentication.getPrincipal()).getUsername();
        queueService.addToQueue(email);
        return ResponseEntity.ok("You have been added to the queue.");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> update(@PathVariable int id, @RequestParam QueueStatus queueStatus) {
        return queueService.update(id, queueStatus);
    }

    @GetMapping("/show")
    public Map<String, Integer> show() {
        List<Queue> queues = queueService.getQueue();
        int queueCount = queues.size();
        return Map.of("totalQueues", queueCount);
    }
}
