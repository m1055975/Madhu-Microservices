package com.mindtree.userservice.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class FoodDto implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String foodId;
    
	private String foodName;
	
	private int quantity;
	
	private double foodPrice;
	
	private String foodType;
	
	@JsonIgnore
	private RestaurantDto restaurant;
	
	
	public FoodDto() {
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
	public RestaurantDto getRestaurant() {
		return restaurant;
	}
	public void setRestaurant(RestaurantDto restaurant) {
		this.restaurant = restaurant;
	}

	

}