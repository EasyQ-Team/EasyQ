package com.restaurantQueue.RestaurantQueue.helper;

import com.restaurantQueue.RestaurantQueue.model.Menu.MenuCategory;
import com.restaurantQueue.RestaurantQueue.model.Menu.MenuItems;
import com.restaurantQueue.RestaurantQueue.request.Menu.MenuCategoryRequest;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MenuCategoryMapper {
    public MenuCategory toMenuCategory(MenuCategoryRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("MenuCategoryRequest cannot be null");
        }

        MenuCategory menuCategory = new MenuCategory();
        menuCategory.setName(request.getName());
        menuCategory.setDescription(request.getDescription());

        if (request.getItems() != null && !request.getItems().isEmpty()) {
            List<MenuItems> menuItemsList = request.getItems().stream().map(itemRequest -> {
                if (itemRequest.getName() == null || itemRequest.getPrice() == null) {
                    throw new IllegalArgumentException("Menu item name and price are required");
                }

                MenuItems menuItem = new MenuItems();
                menuItem.setName(itemRequest.getName());
                menuItem.setDescription(itemRequest.getDescription());
                menuItem.setPrice(itemRequest.getPrice());
                menuItem.setImageUrl(itemRequest.getImageUrl());
                menuItem.setCategory(menuCategory);
                return menuItem;
            }).collect(Collectors.toList());

            menuCategory.setItems(menuItemsList);
        }

        return menuCategory;
    }
}
