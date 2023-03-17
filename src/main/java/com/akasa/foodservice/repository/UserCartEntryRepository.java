package com.akasa.foodservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akasa.foodservice.entity.UserCartEntry;
import com.akasa.foodservice.entity.UserCartEntry.UserCartEntryKey;

@Repository
public interface UserCartEntryRepository extends JpaRepository<UserCartEntry, UserCartEntryKey>,
	CustomUserCartRepository {

	public List<UserCartEntry> findByUserId(Long userId);

}
