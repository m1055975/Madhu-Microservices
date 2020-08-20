package com.mindtree.orderservice.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;


public class LocationDto implements Serializable {


	private static final long serialVersionUID = 1L;

	private String locationId;
	
	private String locationName;
	
	@JsonIgnore
	private List<RestaurantDto> restaurants;
	

	public LocationDto() {
		super();
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public List<RestaurantDto> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(List<RestaurantDto> restaurants) {
		this.restaurants = restaurants;
	}

	
}
