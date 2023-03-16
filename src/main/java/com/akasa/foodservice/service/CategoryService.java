package com.akasa.foodservice.service;

import java.util.List;

import com.akasa.foodservice.entity.FoodItem;

public interface CategoryService {
	List<FoodItem> listItemsForCategory(Long categoryId);
}
