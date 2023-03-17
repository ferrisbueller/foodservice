package com.akasa.foodservice.repository;

import com.akasa.foodservice.entity.FoodServiceUser;
import com.akasa.foodservice.entity.Order;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class CustomOrderRepositoryImpl implements  CustomOrderRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public Order createNewOrder(FoodServiceUser user, Double orderPrice) {
		Order order = new Order();
		order.setUser(user);
		order.setOrderPrice(orderPrice);
		entityManager.persist(order);
		entityManager.flush();
		return order;
	}
}
