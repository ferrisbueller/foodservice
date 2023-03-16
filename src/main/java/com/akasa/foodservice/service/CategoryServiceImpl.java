package com.akasa.foodservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.akasa.foodservice.entity.FoodCategory;
import com.akasa.foodservice.entity.FoodItem;
import com.akasa.foodservice.repository.CategoryRepository;

public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	@Override
	public List<FoodItem> listItemsForCategory(Long categoryId) {
		Optional<FoodCategory> category = categoryRepository.findById(categoryId);
		List<FoodItem> foodItemList = new ArrayList<>();
		category.ifPresent(x -> foodItemList.addAll(x.getFoodItems()));
		return foodItemList;
	}
}
