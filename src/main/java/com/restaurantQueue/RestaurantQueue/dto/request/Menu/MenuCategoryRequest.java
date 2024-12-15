package com.restaurantQueue.RestaurantQueue.dto.request.Menu;

import lombok.Data;
import java.util.List;

@Data
public class MenuCategoryRequest {
    private String name;

    private String description;

    private List<MenuItemRequest> items;
}
