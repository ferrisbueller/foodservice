package com.akasa.foodservice.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;

@jakarta.persistence.Entity
@jakarta.persistence.Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name="user_id", referencedColumnName = "id")
	private FoodServiceUser user;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.REMOVE)
	public List<OrderEntry> orderEntries;

	Double orderPrice;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FoodServiceUser getUser() {
		return user;
	}

	public void setUser(FoodServiceUser user) {
		this.user = user;
	}

	public List<OrderEntry> getOrderEntries() {
		return orderEntries;
	}

	public void setOrderEntries(List<OrderEntry> orderEntries) {
		this.orderEntries = orderEntries;
	}

	public Double getOrderPrice() {
		return orderPrice;
	}

	public void setOrderPrice(Double orderPrice) {
		this.orderPrice = orderPrice;
	}
}
