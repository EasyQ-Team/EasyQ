package com.restaurantQueue.RestaurantQueue.request.Menu;

import com.google.firebase.database.annotations.NotNull;
import lombok.Data;
import java.util.List;

@Data
public class MenuCategoryRequest {
    private String name;

    private String description;

    private List<MenuItemRequest> items;
}
