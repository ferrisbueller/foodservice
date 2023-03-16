package com.akasa.foodservice.payload;

import java.util.List;

public class AddToCartResponse {

	String message;

	List<AddToCartFoodItemEntry> failedItems;

	public AddToCartResponse(String message, List<AddToCartFoodItemEntry> failedItems) {
		this.message = message;
		this.failedItems = failedItems;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<AddToCartFoodItemEntry> getFailedItems() {
		return failedItems;
	}

	public void setFailedItems(
		List<AddToCartFoodItemEntry> failedItems) {
		this.failedItems = failedItems;
	}

}
