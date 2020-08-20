package com.mindtree.searchservice.entity;
import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="review_details")
public class Review implements Serializable{
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "review_id")
	private int reviewId;
	
	@Column(name = "user_id")
	private String userId;
	
	@Column(name = "rating")
	private double rating;
	
	@Column(name = "comment")
	private String comment;
	
	@ManyToOne(cascade = CascadeType.MERGE , fetch = FetchType.LAZY)
	@JoinColumn(name="restaurant_Id")
	private Restaurant restaurant;
	

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public int getReviewId() {
		return reviewId;
	}

	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}
	
	

}
