package com.akasa.foodservice.payload;

import com.akasa.foodservice.entity.OrderEntry;

public class OrderHistoryEntryPayload {

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Long getInventoryBought() {
		return inventoryBought;
	}

	public void setInventoryBought(Long inventoryBought) {
		this.inventoryBought = inventoryBought;
	}

	public Double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}

	public FoodCategoryPayload getCategoryPayload() {
		return categoryPayload;
	}

	public void setCategoryPayload(FoodCategoryPayload categoryPayload) {
		this.categoryPayload = categoryPayload;
	}

	Long itemId;
	String itemName;
	Long inventoryBought;
	Double buyPrice;
	FoodCategoryPayload categoryPayload;

	public OrderHistoryEntryPayload(OrderEntry entry) {
		this.itemId = entry.getItem().getId();
		this.itemName = entry.getItem().getName();
		this.categoryPayload = new FoodCategoryPayload(entry.getItem().getCategory());
		this.inventoryBought = entry.getInventoryCount();
		this.buyPrice = entry.getBuyPrice();
	}

	public OrderHistoryEntryPayload() {}

}
