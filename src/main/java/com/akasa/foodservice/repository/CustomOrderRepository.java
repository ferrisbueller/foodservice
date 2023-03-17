package com.akasa.foodservice.repository;

import com.akasa.foodservice.entity.FoodServiceUser;
import com.akasa.foodservice.entity.Order;

public interface CustomOrderRepository {
	Order createNewOrder(FoodServiceUser user, Double orderPrice);
}

