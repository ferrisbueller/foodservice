package com.akasa.foodservice.payload;

import java.util.List;

public class AddToCartRequest {

	List<AddToCartFoodItemEntry> itemsToAdd;

	public List<AddToCartFoodItemEntry> getItemsToAdd() {
		return itemsToAdd;
	}

	public void setItemsToAdd(
		List<AddToCartFoodItemEntry> itemsToAdd) {
		this.itemsToAdd = itemsToAdd;
	}





}
