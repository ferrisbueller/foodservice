package com.akasa.foodservice.repository;

import com.akasa.foodservice.entity.FoodItem;
import com.akasa.foodservice.entity.Order;
import com.akasa.foodservice.entity.OrderEntry;

public interface CustomOrderEntryRepository {

	OrderEntry createNewEntry(Order order, FoodItem item, Long inventoryCount);


}
