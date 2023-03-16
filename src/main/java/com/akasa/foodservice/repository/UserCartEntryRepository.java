package com.akasa.foodservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akasa.foodservice.entity.UserCartEntry;

public interface UserCartEntryRepository extends JpaRepository<UserCartEntry, Long>,
	CustomUserCartRepository {

	public List<UserCartEntry> findByUserId(Long userId);

}
