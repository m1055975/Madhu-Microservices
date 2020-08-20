package com.mindtree.orderservice.dto;


import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class ReviewDto implements Serializable {
	
    
	private static final long serialVersionUID = 1L;
    
	@JsonIgnore
	private int reviewId;
	
	private String userId;
	
	private double rating;
	
	private String comment;
	
	@JsonIgnore
	private RestaurantDto restaurant;

	public ReviewDto() {
		super();
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public RestaurantDto getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(RestaurantDto restaurant) {
		this.restaurant = restaurant;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	

}
