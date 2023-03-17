package com.akasa.foodservice.repository;

import com.akasa.foodservice.entity.FoodItem;
import com.akasa.foodservice.entity.Order;
import com.akasa.foodservice.entity.OrderEntry;
import com.akasa.foodservice.entity.OrderEntry.OrderEntryKey;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class CustomOrderEntryRepositoryImpl implements CustomOrderEntryRepository{

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public OrderEntry createNewEntry(Order order, FoodItem item, Long inventoryCount) {
		OrderEntryKey entryKey = new OrderEntryKey(order.getId(), item.getId());
		OrderEntry entry = new OrderEntry();
		entry.setId(entryKey);
		entry.setOrder(order);
		entry.setItem(item);
		entry.setInventoryCount(inventoryCount);
		entry.setBuyPrice(item.getPrice());
		entityManager.persist(entry);
		entityManager.flush();
		return entry;
	}
}
