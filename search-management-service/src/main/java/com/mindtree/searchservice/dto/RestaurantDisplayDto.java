package com.mindtree.searchservice.dto;

import java.util.List;


public class RestaurantDisplayDto {
	
	 private String restaurantId;
		
	 private String restaurantName;
	 
	 private double rating;
		
	 private String address;
		
	 private String phoneNumber;
		
	 private double minimumOrderAmount;
	 
	 private double deliveryTime;
	 
	 private int offerInPercentage;
	 
	 private List<FoodDto> foods;
	 
	 private List<ReviewDto> reviews;

	public RestaurantDisplayDto() {
		super();
	}

	public String getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(String restaurantId) {
		this.restaurantId = restaurantId;
	}

	public String getRestaurantName() {
		return restaurantName;
	}

	public void setRestaurantName(String restaurantName) {
		this.restaurantName = restaurantName;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public double getMinimumOrderAmount() {
		return minimumOrderAmount;
	}

	public void setMinimumOrderAmount(double minimumOrderAmount) {
		this.minimumOrderAmount = minimumOrderAmount;
	}

	public double getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(double deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public List<FoodDto> getFoods() {
		return foods;
	}

	public void setFoods(List<FoodDto> foods) {
		this.foods = foods;
	}

	public List<ReviewDto> getReviews() {
		return reviews;
	}

	public void setReviews(List<ReviewDto> reviews) {
		this.reviews = reviews;
	}

	public int getOfferInPercentage() {
		return offerInPercentage;
	}

	public void setOfferInPercentage(int offerInPercentage) {
		this.offerInPercentage = offerInPercentage;
	}

	 

}
