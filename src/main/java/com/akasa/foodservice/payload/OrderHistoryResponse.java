package com.akasa.foodservice.payload;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.akasa.foodservice.entity.Order;

public class OrderHistoryResponse {

	public List<OrderHistoryPayload> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderHistoryPayload> orders) {
		this.orders = orders;
	}

	List<OrderHistoryPayload> orders;

	public OrderHistoryResponse(Set<Order> orders){
		this.orders = orders.stream().map(OrderHistoryPayload::new).
			collect(Collectors.toList());
	}

	public OrderHistoryResponse() {}
}
