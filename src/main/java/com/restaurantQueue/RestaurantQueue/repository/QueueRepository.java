package com.restaurantQueue.RestaurantQueue.repository;

import com.restaurantQueue.RestaurantQueue.model.Queue.Queue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface QueueRepository extends JpaRepository<Queue, Integer> {
    @Query("SELECT COUNT(q) FROM Queue q WHERE q.status = 'Pending'")
    int countPendingQueues();
}
