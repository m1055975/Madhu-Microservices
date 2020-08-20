package com.mindtree.orderservice.dto;

public class CartFoodDto {
	
	private String foodId;
	
	private int quantity;

	public CartFoodDto() {
		super();
		
	}

	public String getFoodId() {
		return foodId;
	}

	public void setFoodId(String foodId) {
		this.foodId = foodId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	

}