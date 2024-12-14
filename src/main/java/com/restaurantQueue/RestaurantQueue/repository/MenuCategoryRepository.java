package com.restaurantQueue.RestaurantQueue.repository;

import com.restaurantQueue.RestaurantQueue.model.Menu.MenuCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuCategoryRepository extends JpaRepository<MenuCategory, Integer> {}
