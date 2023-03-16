package com.akasa.foodservice.payload;

import com.akasa.foodservice.entity.FoodCategory;

public class FoodCategoryPayload {

	public Long getId() {
		return id;
	}

	Long id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	String name;

	public FoodCategoryPayload(FoodCategory category) {
		this.id = category.getId();
		this.name = category.getName();
	}
}
