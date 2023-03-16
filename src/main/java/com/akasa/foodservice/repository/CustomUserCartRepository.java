package com.akasa.foodservice.repository;

import java.util.Optional;

import com.akasa.foodservice.entity.FoodItem;
import com.akasa.foodservice.entity.FoodServiceUser;
import com.akasa.foodservice.entity.UserCartEntry;

public interface CustomUserCartRepository {

	 boolean saveUserCartEntry(FoodServiceUser user, FoodItem foodItem, Long requestInventory,
		UserCartEntry existingEntry);

}
