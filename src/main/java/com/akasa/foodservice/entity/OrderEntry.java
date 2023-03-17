package com.akasa.foodservice.entity;

import java.io.Serializable;

import com.akasa.foodservice.entity.UserCartEntry.UserCartEntryKey;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@jakarta.persistence.Entity
@jakarta.persistence.Table(name = "order_entries")
public class OrderEntry {

	@Embeddable
	public static class OrderEntryKey implements Serializable {

		@Column(name = "order_id")
		Long orderId;

		@Column(name = "item_id")
		Long itemId;

		public OrderEntryKey(Long id, Long id1) {
			this.orderId = id;
			this.itemId = id1;
		}

		public OrderEntryKey() {

		}
	}

	@EmbeddedId
	OrderEntryKey id;

	@ManyToOne
	@MapsId("orderId")
	@JoinColumn(name = "order_id")
	@JsonIgnore
	Order order;

	@ManyToOne
	@MapsId("itemId")
	@JoinColumn(name = "item_id")
	FoodItem item;

	Long inventoryCount;

	public Double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}

	Double buyPrice = 0.0;

	public OrderEntryKey getId() {
		return id;
	}

	public void setId(OrderEntryKey id) {
		this.id = id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
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
