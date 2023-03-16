package com.akasa.foodservice.payload;

import com.akasa.foodservice.entity.FoodItem;
import com.akasa.foodservice.entity.UserCartEntry;

public class FoodItemCartEntryPayload {

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FoodCategoryPayload getCategory() {
		return category;
	}

	public void setCategory(FoodCategoryPayload category) {
		this.category = category;
	}

	public Long getInventoryInCart() {
		return inventoryInCart;
	}

	public void setInventoryInCart(Long inventoryInCart) {
		this.inventoryInCart = inventoryInCart;
	}

	public Long getTotalInventory() {
		return totalInventory;
	}

	public void setTotalInventory(Long totalInventory) {
		this.totalInventory = totalInventory;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	Long id;

	FoodCategoryPayload category;

	Long inventoryInCart;

	Long totalInventory;

	Double price;

	public FoodItemCartEntryPayload(UserCartEntry cartEntry) {
		this.id = cartEntry.getItem().getId();
		this.category = new FoodCategoryPayload(cartEntry.getItem().getCategory());
		this.inventoryInCart = cartEntry.getInventoryCount();
		this.totalInventory = cartEntry.getItem().getInventorySize();
		this.price = cartEntry.getItem().getPrice();
	}

}
