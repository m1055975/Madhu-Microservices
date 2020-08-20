package com.mindtree.searchservice.entity;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="location_Details")
public class Location implements Serializable {
	
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "location_Id")
	private String locationId;
	
	@Column(name="location_name", unique = true)
	private String locationName;
	
	@OneToMany(mappedBy="location",cascade= {CascadeType.PERSIST,CascadeType.REMOVE})
	private List<Restaurant> restaurants;
	

	public Location() {
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

	public List<Restaurant> getRestaurants() {
		return restaurants;
	}

	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}
	
	
	
}