package com.restaurantQueue.RestaurantQueue.model.Order;

import com.restaurantQueue.RestaurantQueue.helper.OrderStatus;
import com.restaurantQueue.RestaurantQueue.model.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name = "orders")
public class Order extends BaseEntity {

    @Column(nullable = false)
    private Integer tableNumber;
    private String customerName;
    @Column(nullable = false)
    private Double totalPrice;
    @Column(nullable = false)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItems> orderItems;
}
