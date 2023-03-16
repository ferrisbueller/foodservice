package com.akasa.foodservice.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akasa.foodservice.entity.FoodServiceERole;
import com.akasa.foodservice.entity.FoodServiceRole;

@Repository
public interface RoleRepository extends JpaRepository<FoodServiceRole, Long> {
	Optional<FoodServiceRole> findByName(FoodServiceERole name);
}