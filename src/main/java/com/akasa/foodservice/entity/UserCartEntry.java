package com.akasa.foodservice.entity;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.UniqueConstraint;

@jakarta.persistence.Entity
@jakarta.persistence.Table(name = "user_cart_entry")
public class UserCartEntry {
	@Embeddable
	public static class UserCartEntryKey implements Serializable {

		@Column(name = "user_id")
		Long userId;

		@Column(name = "item_id")
		Long itemId;

		public UserCartEntryKey(Long id, Long id1) {
			this.userId = id;
			this.itemId = id1;
		}

		public UserCartEntryKey() {

		}
	}

	@EmbeddedId
	UserCartEntryKey id;

	@ManyToOne
	@MapsId("userId")
	@JoinColumn(name = "user_id")
	@JsonIgnore
	FoodServiceUser user;

	@ManyToOne
	@MapsId("itemId")
	@JoinColumn(name = "item_id")
	FoodItem item;

	Long inventoryCount;

	public UserCartEntryKey getId() {
		return id;
	}

	public void setId(UserCartEntryKey id) {
		this.id = id;
	}

	public FoodServiceUser getUser() {
		return user;
	}

	public void setUser(FoodServiceUser user) {
		this.user = user;
	}

	public FoodItem getItem() {
		return item;
	}

	public void setItem(FoodItem item) {
		this.item = item;
	}

	public Long getInventoryCount() {
		return inventoryCount;
	}

	public void setInventoryCount(Long inventoryCount) {
		this.inventoryCount = inventoryCount;
	}
}
