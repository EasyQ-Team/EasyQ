package com.restaurantQueue.RestaurantQueue.dto.request.Menu;

//import com.google.firebase.database.annotations.NotNull;
import lombok.Data;

@Data
public class MenuItemRequest {
    private String name;

    private String description;

//    @NotNull
    private Double price;

    private String imageUrl;
}