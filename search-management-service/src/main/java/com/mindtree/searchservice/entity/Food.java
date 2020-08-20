package com.mindtree.searchservice.entity;
import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="food_Details")
public class Food implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "food_Id")
	private String foodId;
	
	@Column(name = "food_name")
	private String foodName;
	
	@Column(name = "quantity")
	private int quantity;
	
	@Column(name = "food_price")
	private double foodPrice;
	
	@Column(name = "food_type")
	private String foodType;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="restaurant_Id")
	private Restaurant restaurant;

	public Food() {
		super();
	}

	public String getFoodId() {
		return foodId;
	}

	public void setFoodId(String foodId) {
		this.foodId = foodId;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getFoodPrice() {
		return foodPrice;
	}

	public void setFoodPrice(double foodPrice) {
		this.foodPrice = foodPrice;
	}

	public String getFoodType() {
		return foodType;
	}

	public void setFoodType(String foodType) {
		this.foodType = foodType;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	
	
}
