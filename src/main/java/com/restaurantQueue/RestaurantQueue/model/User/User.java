package com.restaurantQueue.RestaurantQueue.model.User;

import com.restaurantQueue.RestaurantQueue.model.BaseEntity;
import com.restaurantQueue.RestaurantQueue.model.Queue.Queue;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SuperBuilder
@Table(name = "customer")
public class User extends BaseEntity {
    private String username;
    @Column(unique = true, nullable = false)
    private String email;
    private String password;
    private String phoneNumber;

    private String roles;

    private String token;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Queue queue;
}
