package com.restaurantQueue.RestaurantQueue.service.Menu;

import com.restaurantQueue.RestaurantQueue.helper.MenuCategoryMapper;
import com.restaurantQueue.RestaurantQueue.model.Menu.MenuCategory;
import com.restaurantQueue.RestaurantQueue.repository.MenuCategoryRepository;
import com.restaurantQueue.RestaurantQueue.dto.request.Menu.MenuCategoryRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuCategoryService {
    @Autowired
    private MenuCategoryRepository menuCategoryRepository;

    @Autowired
    private MenuCategoryMapper menuCategoryMapper;

    public ResponseEntity<List<MenuCategory>> getAll() {
        return new ResponseEntity<>(menuCategoryRepository.findAll(), HttpStatus.FOUND);
    }

    public ResponseEntity<MenuCategory> getById(Integer id) {
        MenuCategory menuCategory = menuCategoryRepository.findById(id).get();
        return new ResponseEntity<>(menuCategory, HttpStatus.FOUND);
    }

    public ResponseEntity<String> create(MenuCategoryRequest menuCategoryRequest) {
        MenuCategory menuCategory = menuCategoryMapper.toMenuCategory(menuCategoryRequest);
        menuCategoryRepository.save(menuCategory);
        return new ResponseEntity<>("Created Successfully", HttpStatus.CREATED);
    }
}
