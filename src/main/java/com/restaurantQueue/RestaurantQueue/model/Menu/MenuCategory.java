package com.restaurantQueue.RestaurantQueue.model.Menu;

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
@Table(name = "menu_category")
public class MenuCategory extends BaseEntity {
    @Column(nullable = false)
    private String name;
    private String description;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MenuItems> items;
}
