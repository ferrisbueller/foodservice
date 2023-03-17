package com.akasa.foodservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.akasa.foodservice.entity.Order;

@Repository
public interface OrderRepository extends CustomOrderRepository, JpaRepository<Order, Long> {

}
