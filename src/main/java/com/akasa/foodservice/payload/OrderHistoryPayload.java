package com.akasa.foodservice.payload;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.akasa.foodservice.entity.Order;

public class OrderHistoryPayload {


	List<OrderHistoryEntryPayload> orderEntries;

	public List<OrderHistoryEntryPayload> getOrderEntries() {
		return orderEntries;
	}

	public void setOrderEntries(
		List<OrderHistoryEntryPayload> orderEntries) {
		this.orderEntries = orderEntries;
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

	Long orderId;
	Double totalOrderPrice;

	public OrderHistoryPayload(Order order) {
		this.orderId = order.getId();
		this.totalOrderPrice = order.getOrderPrice();
		orderEntries = order.getOrderEntries().stream().
			map(OrderHistoryEntryPayload::new).collect(Collectors.toList());
	}

	public OrderHistoryPayload() {}


}
