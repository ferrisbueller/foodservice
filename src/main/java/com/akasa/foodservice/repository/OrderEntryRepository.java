package com.akasa.foodservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akasa.foodservice.entity.OrderEntry;
import com.akasa.foodservice.entity.OrderEntry.OrderEntryKey;

public interface OrderEntryRepository extends CustomOrderEntryRepository, JpaRepository<OrderEntry, OrderEntryKey> {

}
