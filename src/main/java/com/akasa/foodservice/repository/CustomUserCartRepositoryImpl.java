package com.akasa.foodservice.repository;

import java.util.List;

import com.akasa.foodservice.entity.FoodItem;
import com.akasa.foodservice.entity.FoodServiceUser;
import com.akasa.foodservice.entity.UserCartEntry;
import com.akasa.foodservice.entity.UserCartEntry.UserCartEntryKey;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

public class CustomUserCartRepositoryImpl implements CustomUserCartRepository  {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	@Transactional
	public boolean saveUserCartEntry(FoodServiceUser user, FoodItem foodItem, Long requestInventory,
		UserCartEntry existingEntry) {
		if (requestInventory + (existingEntry != null ?
			existingEntry.getInventoryCount() : 0) <= foodItem.getInventorySize()) {
			// can add
			if (existingEntry != null) {
				existingEntry.setInventoryCount(existingEntry.getInventoryCount() + requestInventory);
				entityManager.persist(existingEntry);
			} else {
				UserCartEntry cartEntry = new UserCartEntry();
				cartEntry.setUser(user);
				cartEntry.setItem(foodItem);
				cartEntry.setInventoryCount(requestInventory);
				cartEntry.setId(new UserCartEntryKey(user.getId(), foodItem.getId()));
				entityManager.persist(cartEntry);
			}
		}
		entityManager.flush();
		return  true;
	}

}
