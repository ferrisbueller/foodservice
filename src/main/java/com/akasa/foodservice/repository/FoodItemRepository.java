package com.akasa.foodservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akasa.foodservice.entity.FoodItem;

public interface FoodItemRepository extends JpaRepository<FoodItem, Long> {

}
