package com.mindtree.searchservice.dao;

import java.util.List;

import com.mindtree.searchservice.dto.FoodDto;
import com.mindtree.searchservice.dto.LocationDto;
import com.mindtree.searchservice.dto.RestaurantDisplayDto;
import com.mindtree.searchservice.dto.RestaurantDto;
import com.mindtree.searchservice.dto.ReviewDto;
import com.mindtree.searchservice.exception.DaoException;
import com.mindtree.searchservice.exception.ServiceException;

public interface SearchDao {

	FoodDto addFoodToRestaurant(String restaurantId, FoodDto foodDto) throws DaoException;

	LocationDto addLocation(LocationDto locationDto) throws DaoException;

	RestaurantDto addRestaurantToLocation(String locationId, RestaurantDto restaurantDto) throws ServiceException;

	List<RestaurantDisplayDto> getAllRestaurantsByGivingDistance(Double requiredDistance, Double lattitudeOfUser,
			Double longitudeOfUser) throws DaoException;

	ReviewDto addReviewToRestaurant(String restaurantId, ReviewDto reviewDto) throws DaoException;

	List<RestaurantDisplayDto> getAllRestaurantsByGivingCuisineType(String foodType, Double lattitudeOfUser, Double longitudeOfUser) throws ServiceException;

	List<RestaurantDisplayDto> getAllRestaurantsByGivingBudget(Double budget, Double lattitudeOfUser, Double longitudeOfUser) throws DaoException;

	List<RestaurantDisplayDto> getAllRestaurantsByGivingRating(Double rating, Double lattitudeOfUser,
			Double longitudeOfUser) throws DaoException;

	List<RestaurantDisplayDto> getAllRestaurantsByGivingName(String restaurantName, Double lattitudeOfUser,
			Double longitudeOfUser) throws DaoException;

	RestaurantDto getRestaurantDetailsFromRestaurantId(String restaurantId) throws DaoException;

	String removeQuantity(String foodId, int quantity, String restaurantId) throws DaoException;


	FoodDto checkFoodAvailibility(String foodId, String restaurantId) throws DaoException, ServiceException;


}
