package com.mindtree.searchservice.service.impl;
import java.util.List;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mindtree.searchservice.dao.SearchDao;
import com.mindtree.searchservice.dto.FoodDto;
import com.mindtree.searchservice.dto.LocationDto;
import com.mindtree.searchservice.dto.RestaurantDisplayDto;
import com.mindtree.searchservice.dto.RestaurantDto;
import com.mindtree.searchservice.dto.ReviewDto;
import com.mindtree.searchservice.exception.DaoException;
import com.mindtree.searchservice.exception.ServiceException;
import com.mindtree.searchservice.exception.ValidationException;
import com.mindtree.searchservice.service.SearchService;

@Service
public class SearchServiceImplementation implements SearchService {
	
	@Autowired
	private SearchDao searchDao;
	
	 private final Logger logger = LogManager.getLogger(SearchServiceImplementation.class);


	@Override
	public FoodDto addFoodToRestaurant(String restaurantId, FoodDto foodDto) throws ServiceException, DaoException {
		
		if(restaurantId.length() != 10){
		   throw new ValidationException("Restaurant Id is Incorrect !!!(Length = 10)");
		 }
		else if(foodDto.getFoodId().length() != 7){
			throw new ValidationException("Food Id is Incorrect !!!(Length = 7)");
		}
		else{
			try {
			return searchDao.addFoodToRestaurant(restaurantId ,foodDto);
			}
			catch(DaoException e){
				logger.error(ExceptionUtils.getFullStackTrace(e));
				throw new ServiceException(e.getMessage());
		}
		 }
	
	}

	@Override
	public RestaurantDto addRestaurantToLocation(String locationId, RestaurantDto restaurantDto) throws ServiceException {
		if(locationId.length() != 5){
			throw new ValidationException("Location ID inCorrect ! (Length = 5)");
		}
		else if(restaurantDto.getRestaurantId().length() != 10){
		   throw new ValidationException("Restaurant Id is Incorrect ! (Length = 10)");
		 }
		else{
			try {
		 return searchDao.addRestaurantToLocation(locationId , restaurantDto);
			}
			catch(DaoException e){
				logger.error(ExceptionUtils.getFullStackTrace(e));
				throw new ServiceException(e.getMessage());
		}
		}
	}

	@Override
	public LocationDto addLocation(LocationDto locationDto) throws ServiceException {
		
		if(locationDto.getLocationId().length() != 5){
			throw new ValidationException("Location ID inCorrect ! (Length = 5)");
		}
		else{
			try {
		return searchDao.addLocation(locationDto);
			}
			catch(DaoException e){
				logger.error(ExceptionUtils.getFullStackTrace(e));
				throw new ServiceException(e.getMessage());
		}
	}
	}
	
	@Override
	public ReviewDto addReviewToRestaurant(String restaurantId, ReviewDto reviewDto) throws ServiceException {
		
		if(restaurantId.length() != 10){
			throw new ValidationException("Restaurant Id is Incorrect ! (Length = 10)");
		}
		else if(reviewDto.getUserId().length() != 10){
			throw new ValidationException("User Id is Invalid ! (Length = 10)");
		}
		else {
			try {
			return searchDao.addReviewToRestaurant(restaurantId , reviewDto);
			}
			catch(DaoException e){
				logger.error(ExceptionUtils.getFullStackTrace(e));
				throw new ServiceException(e.getMessage());
		}
	}
	}
	@Override
	public List<RestaurantDisplayDto> getAllRestaurantsByGivingDistance(Double distance, Double lattitudeOfUser,
			Double longitudeOfUser) throws ServiceException {
		try {
		return searchDao.getAllRestaurantsByGivingDistance(distance , lattitudeOfUser , longitudeOfUser);
		}
		catch(DaoException e){
			logger.error(ExceptionUtils.getFullStackTrace(e));
			throw new ServiceException(e.getMessage());
	}
}

	@Override
	public List<RestaurantDisplayDto> getAllRestaurantsByGivingCuisineType(String foodType , Double lattitudeOfUser ,Double longitudeOfUser) throws ServiceException {
		try {
		return searchDao.getAllRestaurantsByGivingCuisineType(foodType ,lattitudeOfUser , longitudeOfUser);
		}
		catch(DaoException e){
			logger.error(ExceptionUtils.getFullStackTrace(e));
			throw new ServiceException(e.getMessage());
	}
	}

	@Override
	public List<RestaurantDisplayDto> getAllRestaurantsByGivingBudget(Double budget, Double lattitudeOfUser ,Double longitudeOfUser) throws ServiceException {
		try {
		return searchDao.getAllRestaurantsByGivingBudget(budget,lattitudeOfUser, longitudeOfUser);
		}
		catch(DaoException e){
			logger.error(ExceptionUtils.getFullStackTrace(e));
			throw new ServiceException(e.getMessage());
	}
	}

	@Override
	public List<RestaurantDisplayDto> getAllRestaurantsByGivingRating(Double rating, Double lattitudeOfUser,
			Double longitudeOfUser) throws ServiceException {
		try {
		return searchDao.getAllRestaurantsByGivingRating(rating,lattitudeOfUser, longitudeOfUser);
		}
		catch(DaoException e){
			logger.error(ExceptionUtils.getFullStackTrace(e));
			throw new ServiceException(e.getMessage());
	}
	}

	@Override
	public List<RestaurantDisplayDto> getAllRestaurantsByGivingName(String restaurantName, Double lattitudeOfUser,
			Double longitudeOfUser) throws ServiceException {
		try {
		return searchDao.getAllRestaurantsByGivingName(restaurantName , lattitudeOfUser , longitudeOfUser);
		}
		catch(DaoException e){
			logger.error(ExceptionUtils.getFullStackTrace(e));
			throw new ServiceException(e.getMessage());
	}
	}

	@Override
	public RestaurantDto getRestaurantDetailsFromRestaurantId(String restaurantId) throws ServiceException {
		try {
		return searchDao.getRestaurantDetailsFromRestaurantId(restaurantId);
		}
		catch(DaoException e){
			logger.error(ExceptionUtils.getFullStackTrace(e));
			throw new ServiceException(e.getMessage());
	}
	}

	@Override
	public String removeQuantity(String foodId, int quantity, String restaurantId) throws ServiceException {
		try {
		return searchDao.removeQuantity(foodId , quantity , restaurantId );
		}
		catch(DaoException e){
			logger.error(ExceptionUtils.getFullStackTrace(e));
			throw new ServiceException(e.getMessage());
	}
	}

	@Override
	public FoodDto checkFoodAvailability(String foodId, String restaurantId) throws ServiceException {
		try {
		return searchDao.checkFoodAvailibility(foodId , restaurantId);
		}
		catch(DaoException e){
			logger.error(ExceptionUtils.getFullStackTrace(e));
			throw new ServiceException(e.getMessage());
	}
		
		
	}


}
