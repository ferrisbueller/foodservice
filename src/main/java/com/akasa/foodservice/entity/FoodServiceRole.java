package com.akasa.foodservice.entity;


import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@jakarta.persistence.Entity
@jakarta.persistence.Table(name = "roles")
public class FoodServiceRole {

		@Id
		@GeneratedValue(strategy = GenerationType.IDENTITY)
		private Integer id;

		@Enumerated(EnumType.STRING)
		@Column(length = 20)
		private FoodServiceERole name;

		public FoodServiceRole() {

		}

		public FoodServiceRole(FoodServiceERole name) {
			this.name = name;
		}

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public FoodServiceERole getName() {
			return name;
		}

		public void setName(FoodServiceERole name) {
			this.name = name;
		}
	}