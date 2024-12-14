package com.restaurantQueue.RestaurantQueue.model.Order;

import com.restaurantQueue.RestaurantQueue.model.BaseEntity;
import com.restaurantQueue.RestaurantQueue.model.Menu.MenuItems;
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
@Table(name = "order_items")
public class OrderItems extends BaseEntity {
    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "menu_items_id", nullable = false)
    private MenuItems menuItems;

    @Column (nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double subTotal;
}
