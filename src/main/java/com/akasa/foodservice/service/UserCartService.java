package com.akasa.foodservice.service;

import java.util.List;

import com.akasa.foodservice.payload.AddToCartFoodItemEntry;
import com.akasa.foodservice.payload.AddToCartResponse;
import com.akasa.foodservice.payload.CheckoutResponse;
import com.akasa.foodservice.payload.OrderHistoryResponse;

public interface UserCartService {

	public AddToCartResponse addItemsToUserCart(String username,
		List<AddToCartFoodItemEntry> itemsToAdd);

	public CheckoutResponse checkoutUserCart(String username);

	public OrderHistoryResponse getOrderHistory(String username);

}
