package com.akasa.foodservice.entity;

import java.util.HashSet;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.UniqueConstraint;

@jakarta.persistence.Entity
@jakarta.persistence.Table(name = "items",
	uniqueConstraints = {
		@UniqueConstraint(columnNames = {"name" , "category_id"})
	})
public class FoodItem {

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public FoodCategory getCategory() {
		return category;
	}

	public void setCategory(FoodCategory category) {
		this.category = category;
	}

	public Set<UserCartEntry> getCartEntries() {
		return cartEntries;
	}

	public void setCartEntries(Set<UserCartEntry> cartEntries) {
		this.cartEntries = cartEntries;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 100)
	private String name;

	@ManyToOne (fetch = FetchType.LAZY)
	@JoinColumn(name="category_id", referencedColumnName = "id")
	private FoodCategory category;

	public Long getInventorySize() {
		return inventorySize;
	}

	public void setInventorySize(Long inventorySize) {
		this.inventorySize = inventorySize;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	// default value
	@Value("${inventory.size:0}")
	private Long inventorySize;

	// default value
	@Value("${price:0}")
	private Double price;

	@JsonIgnore
	@OneToMany(mappedBy = "item", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	private Set<UserCartEntry> cartEntries;

	@JsonIgnore
	@OneToMany(mappedBy = "item", fetch = FetchType.LAZY)
	private Set<OrderEntry> orderEntries;

	public Set<OrderEntry> getOrderEntries() {
		return orderEntries;
	}

	public void setOrderEntries(Set<OrderEntry> orderEntries) {
		this.orderEntries = orderEntries;
	}
}
