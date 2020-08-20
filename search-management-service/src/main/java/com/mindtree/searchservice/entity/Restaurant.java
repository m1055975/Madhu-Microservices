package com.mindtree.searchservice.entity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="restaurant_Details")
public class Restaurant implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "restaurant_id")
	private String restaurantId;
	
	@Column(name = "restaurant_name")
	private String restaurantName;
	
	@Column(name = "lattitude")
	private double lattitude;
	
	@Column(name = "longitude")
	private double longitude;
	
	@Column(name = "rating")
	private double rating;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "phone_number")
	private String phoneNumber;
	
	@Column(name = "minimum_order_amount")
	private double minimumOrderAmount;
	
	@Column(name = "time_per_distance")
	private double timePerDistance;
	
	@Column(name = "offer_in_percentage")
	private int offerInPercentage;
	
	@Column(name = "delivery_person")
	private String deliveryPerson;
	
	@OneToMany(mappedBy="restaurant",cascade= { CascadeType.PERSIST , CascadeType.REMOVE })
	private List<Food> foods;
	
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="location_Id")
	private Location location;
	
    @OneToMany(mappedBy = "restaurant" ,cascade = { CascadeType.PERSIST , CascadeType.REMOVE} , fetch = FetchType.LAZY)
	private List<Review> reviews;
	

	public Restaurant() {
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

	public double getMinimumOrderAmount() {
		return minimumOrderAmount;
	}

	public void setMinimumOrderAmount(double minimumOrderAmount) {
		this.minimumOrderAmount = minimumOrderAmount;
	}

	public List<Food> getFoods() {
		return foods;
	}

	public void setFoods(List<Food> foods) {
		this.foods = foods;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
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

	public double getTimePerDistance() {
		return timePerDistance;
	}

	public void setTimePerDistance(double timePerDistance) {
		this.timePerDistance = timePerDistance;
	}

	public List<Review> getReviews() {
		return reviews;
	}

	public void setReviews(List<Review> reviews) {
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
