package com.restaurantQueue.RestaurantQueue.repository;

import com.restaurantQueue.RestaurantQueue.model.Menu.MenuItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuItemsRepository extends JpaRepository<MenuItems, Integer> {}
