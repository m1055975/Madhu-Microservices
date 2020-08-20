package com.mindtree.searchservice.service;
import java.util.List;
import com.mindtree.searchservice.dto.FoodDto;
import com.mindtree.searchservice.dto.LocationDto;
import com.mindtree.searchservice.dto.RestaurantDisplayDto;
import com.mindtree.searchservice.dto.RestaurantDto;
import com.mindtree.searchservice.dto.ReviewDto;
import com.mindtree.searchservice.exception.ServiceException;

public interface SearchService {

	FoodDto addFoodToRestaurant(String restaurantId, FoodDto foodDto) throws ServiceException ;

	RestaurantDto addRestaurantToLocation(String locationId, RestaurantDto restaurantDto) throws ServiceException;

	LocationDto addLocation(LocationDto locationDto) throws ServiceException;

	List<RestaurantDisplayDto> getAllRestaurantsByGivingDistance(Double distance, Double lattitudeOfUser, Double longitudeOfUser) throws ServiceException;

	ReviewDto addReviewToRestaurant(String restaurantId, ReviewDto reviewDto) throws ServiceException;

	List<RestaurantDisplayDto> getAllRestaurantsByGivingCuisineType(String foodType, Double lattitudeOfUser, Double longitudeOfUser) throws ServiceException;

	List<RestaurantDisplayDto> getAllRestaurantsByGivingBudget(Double budget, Double lattitudeOfUser, Double longitudeOfUser) throws ServiceException;

	List<RestaurantDisplayDto> getAllRestaurantsByGivingRating(Double rating, Double lattitudeOfUser,
			Double longitudeOfUser) throws ServiceException;

	List<RestaurantDisplayDto> getAllRestaurantsByGivingName(String restaurantName, Double lattitudeOfUser,
			Double longitudeOfUser) throws ServiceException;

	RestaurantDto getRestaurantDetailsFromRestaurantId(String restaurantId) throws ServiceException;

	String removeQuantity(String foodId, int quantity, String restaurantId) throws ServiceException;

	FoodDto checkFoodAvailability(String foodId, String restaurantId) throws ServiceException;


	

}
