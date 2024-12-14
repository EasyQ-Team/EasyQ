package com.restaurantQueue.RestaurantQueue.model.Queue;

import com.restaurantQueue.RestaurantQueue.helper.QueueStatus;
import com.restaurantQueue.RestaurantQueue.model.BaseEntity;
import com.restaurantQueue.RestaurantQueue.model.User.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name = "queue")
public class Queue extends BaseEntity {
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(nullable = false)
    private int queuePosition;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QueueStatus status = QueueStatus.Pending;
}
