package com.mindtree.orderservice.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class RestaurantDto implements Serializable {
	
    
	private static final long serialVersionUID = 1L;

	private String restaurantId;
	
	private String restaurantName;
	
	private double lattitude;
	
	private double longitude;
	
	private double rating;
	
	private String address;
	
	private String phoneNumber;
	
	private double minimumOrderAmount;
	
	private double timePerDistance;
	
	private int offerInPercentage;
	
	private String deliveryPerson;
	
	//@JsonIgnore
	private List<FoodDto> foods;
	
	@JsonIgnore
	private LocationDto location;
	
	@JsonIgnore
	private List<ReviewDto> reviews;

	public RestaurantDto() {
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

	public double getLattitude() {
		return lattitude;
	}

	public void setLattitude(double lattitude) {
		this.lattitude = lattitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
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

	public double getTimePerDistance() {
		return timePerDistance;
	}

	public void setTimePerDistance(double timePerDistance) {
		this.timePerDistance = timePerDistance;
	}

	public List<FoodDto> getFoods() {
		return foods;
	}

	public void setFoods(List<FoodDto> foods) {
		this.foods = foods;
	}

	public LocationDto getLocation() {
		return location;
	}

	public void setLocation(LocationDto location) {
		this.location = location;
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

	public String getDeliveryPerson() {
		return deliveryPerson;
	}

	public void setDeliveryPerson(String deliveryPerson) {
		this.deliveryPerson = deliveryPerson;
	}

	

}
