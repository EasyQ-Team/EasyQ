package com.restaurantQueue.RestaurantQueue.service.Queue;

import com.restaurantQueue.RestaurantQueue.helper.QueueStatus;
import com.restaurantQueue.RestaurantQueue.model.Queue.Queue;
import com.restaurantQueue.RestaurantQueue.model.User.User;
import com.restaurantQueue.RestaurantQueue.repository.QueueRepository;
import com.restaurantQueue.RestaurantQueue.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class QueueService {

    private final QueueRepository queueRepository;

    @Autowired
    private UserRepository userRepository;

    public QueueService(QueueRepository queueRepository) {
        this.queueRepository = queueRepository;
    }

    @Transactional
    public void addToQueue(String username) {
        User user = userRepository.findByEmail(username)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getQueue() != null) {
            throw new RuntimeException("User is already in the queue");
        }

        int nextQueuePosition = queueRepository.countPendingQueues() + 1;

        Queue queue = new Queue();
        queue.setUser(user);
        queue.setQueuePosition(nextQueuePosition);

        queueRepository.save(queue);

        user.setQueue(queue);
        userRepository.save(user);
    }

    public List<Queue> getQueue() {
        return queueRepository.findAll();
    }

    public ResponseEntity<String> update(int id, QueueStatus queueStatus) {
        try {
            Optional<Queue> existingQueue = queueRepository.findById(id);
            if (existingQueue.isPresent()) {
                Queue queue = existingQueue.get();
                queue.setStatus(queueStatus);
                queueRepository.save(queue);
                return ResponseEntity.ok("Queue status updated to served.");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Queue not found.");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred while updating the queue.");
        }
    }
}
