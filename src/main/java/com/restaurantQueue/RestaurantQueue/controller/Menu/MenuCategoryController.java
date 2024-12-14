package com.restaurantQueue.RestaurantQueue.controller.Menu;

import com.restaurantQueue.RestaurantQueue.model.Menu.MenuCategory;
import com.restaurantQueue.RestaurantQueue.request.Menu.MenuCategoryRequest;
import com.restaurantQueue.RestaurantQueue.service.Menu.MenuCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("menu-category")
public class MenuCategoryController {
    @Autowired
    private MenuCategoryService menuCategoryService;

    @GetMapping("/")
    public ResponseEntity<List<MenuCategory>> getAll() {
        return menuCategoryService.getAll();
    }

    @GetMapping("/, {id}")
    public ResponseEntity<MenuCategory> getById(@RequestParam Integer id) {
        return menuCategoryService.getById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(@RequestBody MenuCategoryRequest menuCategoryRequest) {
        return menuCategoryService.create(menuCategoryRequest);
    }
}
