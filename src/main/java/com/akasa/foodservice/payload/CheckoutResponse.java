package com.akasa.foodservice.payload;

public class CheckoutResponse {

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Double getTotalOrderPrice() {
		return totalOrderPrice;
	}

	public void setTotalOrderPrice(Double totalOrderPrice) {
		this.totalOrderPrice = totalOrderPrice;
	}

	public FoodItemCartEntryPayload getFailedItem() {
		return failedItem;
	}

	public void setFailedItem(FoodItemCartEntryPayload failedItem) {
		this.failedItem = failedItem;
	}

	String message;

	Long orderId;

	Double totalOrderPrice;

	FoodItemCartEntryPayload failedItem;

	CheckoutResponse() {

	}

	public CheckoutResponse(String message, Long orderId, Double totalOrderPrice) {
		this.message = message;
		this.orderId = orderId;
		this.totalOrderPrice = totalOrderPrice;
	}

	public CheckoutResponse(FoodItemCartEntryPayload failedItem) {
		this.failedItem = failedItem;
		this.message = "Not Enough Inventory";
	}

	public CheckoutResponse(String message) {
		this.message = message;
	}
}
