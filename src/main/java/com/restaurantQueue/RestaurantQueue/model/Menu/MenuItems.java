package com.restaurantQueue.RestaurantQueue.model.Menu;

import com.restaurantQueue.RestaurantQueue.model.BaseEntity;
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
@Table(name = "menu_items")
public class MenuItems extends BaseEntity {
    @Column(nullable = false)
    private String name;
    private String description;

    @Column(nullable = false)
    private double price;

    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private MenuCategory category;

}
