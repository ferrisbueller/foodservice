package com.akasa.foodservice.repository;


import java.util.Optional;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akasa.foodservice.entity.FoodServiceUser;


@Repository
public interface UserRepository extends JpaRepository<FoodServiceUser, Long> {
	Optional<FoodServiceUser> findByUsername(String username);

	Boolean existsByUsername(String username);

	Boolean existsByEmail(String email);
}