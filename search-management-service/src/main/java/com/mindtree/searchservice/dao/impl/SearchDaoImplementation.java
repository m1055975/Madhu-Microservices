package com.mindtree.searchservice.dao.impl;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import com.mindtree.searchservice.dao.SearchDao;
import com.mindtree.searchservice.dto.FoodDto;
import com.mindtree.searchservice.dto.LocationDto;
import com.mindtree.searchservice.dto.RestaurantDisplayDto;
import com.mindtree.searchservice.dto.RestaurantDto;
import com.mindtree.searchservice.dto.ReviewDto;
import com.mindtree.searchservice.entity.Food;
import com.mindtree.searchservice.entity.Location;
import com.mindtree.searchservice.entity.Restaurant;
import com.mindtree.searchservice.entity.Review;
import com.mindtree.searchservice.exception.AvailibilityException;
import com.mindtree.searchservice.exception.DaoException;
import com.mindtree.searchservice.exception.ServiceException;
import com.mindtree.searchservice.repository.FoodRepository;
import com.mindtree.searchservice.repository.LocationRepository;
import com.mindtree.searchservice.repository.RestaurantRepository;
import com.mindtree.searchservice.repository.ReviewRepository;

@Service
public class SearchDaoImplementation implements SearchDao {
	
	 @Autowired
	 private RestaurantRepository restaurantRepository;
	 
	 @Autowired
	 private FoodRepository foodRepository;
	 
	 @Autowired
	 private LocationRepository locationRepository;
	 
	 @Autowired
	 private ReviewRepository reviewRepository;
	 
	 @Autowired
	 private CacheManager cacheManager;

	 private ModelMapper modelMapper = new ModelMapper();

	@Override
	public FoodDto addFoodToRestaurant(String restaurantId, FoodDto foodDto) throws DaoException {
	int flag = 0;
	Food foodAdded = null;
	if(restaurantRepository.existsById(restaurantId))
	{
		 Restaurant restaurant =restaurantRepository.findById(restaurantId).get();
		 List<Food> foodListFetched =new ArrayList<>(); 
		 foodListFetched=restaurant.getFoods();
		 for(Food food : foodListFetched)
		 {
		    if(food.getFoodName().equalsIgnoreCase(foodDto.getFoodName()))
		    {
			  Food foodFetched = foodRepository.findById(foodDto.getFoodId()).get();
			  foodFetched.setQuantity(foodDto.getQuantity() + foodFetched.getQuantity());
			  foodFetched.setRestaurant(restaurant);
			  flag = 1;
			  return modelMapper.map(foodRepository.save(foodFetched), FoodDto.class);
		    }
		 }
		  if(flag == 0)
		   {
			
		     foodDto.setRestaurant(modelMapper.map(restaurant, RestaurantDto.class));
			 foodAdded = foodRepository.saveAndFlush(modelMapper.map(foodDto, Food.class));
		     
		   }
		    return modelMapper.map(foodAdded, FoodDto.class);
		     
	  }
	else{
		throw new AvailibilityException("Restaurant Not Present !!!");
		}
	
	}

	@Override
	public LocationDto addLocation(LocationDto locationDto) throws DaoException {
		
		if(!locationRepository.existsById(locationDto.getLocationId()))
		{
			 return modelMapper.map(locationRepository.save(modelMapper.map(locationDto, Location.class)), LocationDto.class);
		}
		else
		{
			throw new AvailibilityException("Location is already available in the site !!!");
		}
	}

	@Override
	public RestaurantDto addRestaurantToLocation(String locationId, RestaurantDto restaurantDto) throws ServiceException {
		
		if(locationRepository.existsById(locationId))
		{
			 Location location = locationRepository.findById(locationId).get();
			 List<Restaurant> restaurantListFetched =new ArrayList<>();
			 restaurantListFetched = location.getRestaurants();
			 if(restaurantListFetched == null)
			 {
				restaurantDto.setLocation(modelMapper.map(location ,LocationDto.class));
				return modelMapper.map(restaurantRepository.save(modelMapper.map(restaurantDto, Restaurant.class)), RestaurantDto.class);
			 }
			 for(Restaurant restaurant : restaurantListFetched)
			 {
				 if(restaurant.getRestaurantId().equalsIgnoreCase(restaurantDto.getRestaurantId()))
				 {
					 throw new AvailibilityException("Restaurant Already Exists !!!");
				 }
			 }
			 restaurantDto.setLocation(modelMapper.map(location ,LocationDto.class));
				return modelMapper.map(restaurantRepository.save(modelMapper.map(restaurantDto, Restaurant.class)), RestaurantDto.class);
			 }
		else
		{
			throw new AvailibilityException("Restaurant already Exists !!!");
		}
}

	@Override
	public ReviewDto addReviewToRestaurant(String restaurantId, ReviewDto reviewDto) throws DaoException {
		
		if(restaurantRepository.existsById(restaurantId))
		{
			
			Restaurant restaurantToAddReview = restaurantRepository.findById(restaurantId).get();
			Review review =modelMapper.map(reviewDto, Review.class);
			restaurantToAddReview.setRating((restaurantToAddReview.getRating() * restaurantToAddReview.getReviews().size()) + reviewDto.getRating());
			restaurantToAddReview.setRating(restaurantToAddReview.getRating()/(restaurantToAddReview.getReviews().size()+1));
			review.setRestaurant(restaurantToAddReview);
			reviewRepository.save(review);
			return reviewDto;
		}
		else{
			throw new AvailibilityException("No Such Restaurant !!!");
		}
	}  
	
	@Cacheable(value = "restaurant")
	@Override
	public List<RestaurantDisplayDto> getAllRestaurantsByGivingDistance(Double requiredDistance, Double lattitudeOfUser,
			Double longitudeOfUser) throws DaoException {
		
		List<RestaurantDisplayDto> showRestaurants = new ArrayList<>();
		List<RestaurantDto> restaurantDtos = new ArrayList<>();
		double distance = 0.0;
		int flag =0 ;
		
		List<Restaurant> restaurants = restaurantRepository.findAll();
		
		for(Restaurant restaurant : restaurants )
		{
			distance = distance(lattitudeOfUser, longitudeOfUser, 
                    restaurant.getLattitude(), restaurant.getLongitude());
			if(distance <= requiredDistance)
			{
				restaurantDtos.add(modelMapper.map(restaurant, RestaurantDto.class));
				flag = flag + 1 ;
			}
		}
		if(flag != 0)
		{
		for(RestaurantDto restaurantDto : restaurantDtos)
		{
		   RestaurantDisplayDto restaurantDisplayDto = new RestaurantDisplayDto();
		   restaurantDisplayDto.setRestaurantId(restaurantDto.getRestaurantId());
		   restaurantDisplayDto.setRestaurantName(restaurantDto.getRestaurantName());
		   restaurantDisplayDto.setRating(restaurantDto.getRating());
		   restaurantDisplayDto.setAddress(restaurantDto.getAddress());
		   restaurantDisplayDto.setPhoneNumber(restaurantDto.getPhoneNumber());
		   restaurantDisplayDto.setOfferInPercentage(restaurantDto.getOfferInPercentage());
		   Double distanceParticular = distance(lattitudeOfUser, longitudeOfUser, 
                   restaurantDto.getLattitude(), restaurantDto.getLongitude());
		   restaurantDisplayDto.setMinimumOrderAmount(restaurantDto.getMinimumOrderAmount());
		   restaurantDisplayDto.setDeliveryTime(distanceParticular * restaurantDto.getTimePerDistance());
		   restaurantDisplayDto.setFoods(restaurantDto.getFoods());
		   restaurantDisplayDto.setReviews(restaurantDto.getReviews());
		   showRestaurants.add( restaurantDisplayDto);
		   
		}
		return showRestaurants;
		}
		else
		{
			throw new AvailibilityException("No Restaurant within Distance !!!");
		}	
	}

	private double distance(double lattitudeOfUser, double longitudeOfUser, double lattitude, double longitude) {

		 lattitudeOfUser = Math.toRadians( lattitudeOfUser); 
		 longitudeOfUser = Math.toRadians(longitudeOfUser); 
		 lattitude = Math.toRadians(lattitude); 
		 longitude = Math.toRadians(longitude); 
    
        double dLongitude = longitude - longitudeOfUser;  
        double dLattitude = lattitude - lattitudeOfUser; 
        double a = Math.pow(Math.sin(dLattitude / 2), 2) 
                 + Math.cos(lattitudeOfUser) * Math.cos(lattitude) 
                 * Math.pow(Math.sin( dLongitude / 2),2); 
              
        double c = 2 * Math.asin(Math.sqrt(a)); 
        double r = 6371;  
        return(c * r); 
    }
	@Cacheable(value = "restaurant")
	@Override
	public List<RestaurantDisplayDto> getAllRestaurantsByGivingCuisineType(String foodType , Double lattitudeOfUser, Double longitudeOfUser) throws ServiceException {
		
		List<Food> foods = foodRepository.findAll();
		int flag = 0;
		List<Restaurant> restaurantsWithFoodType = new ArrayList<>();
		List<RestaurantDisplayDto> showRestaurants = new ArrayList<>();
		  for(Food food : foods)
		  {
			if(food.getFoodType().equalsIgnoreCase(foodType))
			{
			   restaurantsWithFoodType.add(food.getRestaurant());
			   flag = flag + 1;
			}
		  }
	 if(flag != 0)
	 {
		  for(Restaurant restaurant : restaurantsWithFoodType)
		  {
			   RestaurantDto restaurantDto = new RestaurantDto();
			   RestaurantDisplayDto restaurantDisplayDto = new RestaurantDisplayDto();
			   restaurantDto = modelMapper.map(restaurant, RestaurantDto.class);
			   restaurantDisplayDto.setRestaurantId(restaurantDto.getRestaurantId());
			   restaurantDisplayDto.setRestaurantName(restaurantDto.getRestaurantName());
			   restaurantDisplayDto.setRating(restaurantDto.getRating());
			   restaurantDisplayDto.setAddress(restaurantDto.getAddress());
			   restaurantDisplayDto.setPhoneNumber(restaurantDto.getPhoneNumber());
			   restaurantDisplayDto.setOfferInPercentage(restaurantDto.getOfferInPercentage());
			   restaurantDisplayDto.setMinimumOrderAmount(restaurant.getMinimumOrderAmount());
			   Double distanceParticular = distance(lattitudeOfUser, longitudeOfUser, 
	                   restaurantDto.getLattitude(), restaurantDto.getLongitude());
			   restaurantDisplayDto.setDeliveryTime(distanceParticular * restaurant.getTimePerDistance());
			   restaurantDisplayDto.setFoods(restaurantDto.getFoods());
			   restaurantDisplayDto.setReviews(restaurantDto.getReviews());
			   showRestaurants.add(restaurantDisplayDto);

		  }
		  return showRestaurants;
	 }
	
		else
		{
			throw new AvailibilityException("This Cuisine is not available Now ! ");
		}
	}
	@Cacheable(value = "restaurant")
	@Override
	public List<RestaurantDisplayDto> getAllRestaurantsByGivingBudget(Double budget , Double lattitudeOfUser, Double longitudeOfUser) throws DaoException {
		
		List<Food> foods = foodRepository.findAll();
		int flag1 = 0;
		List<Restaurant> restaurantsWithBudgetFood = new ArrayList<>();
		List<RestaurantDisplayDto> showRestaurants = new ArrayList<>();
		  for(Food food : foods)
		  {
			if( budget >= food.getFoodPrice() )
			{
			   restaurantsWithBudgetFood.add(food.getRestaurant());
			   flag1 = flag1 + 1;
			}
		  }
	 if(flag1 != 0)
	 {
		  for(Restaurant restaurant : restaurantsWithBudgetFood)
		  {
			   int flag2 =0;
			   RestaurantDto restaurantDto = new RestaurantDto();
			   RestaurantDisplayDto restaurantDisplayDto = new RestaurantDisplayDto();
			   restaurantDto = modelMapper.map(restaurant, RestaurantDto.class);
			   restaurantDisplayDto.setRestaurantId(restaurantDto.getRestaurantId());
			   restaurantDisplayDto.setRestaurantName(restaurantDto.getRestaurantName());
			   restaurantDisplayDto.setRating(restaurantDto.getRating());
			   restaurantDisplayDto.setAddress(restaurantDto.getAddress());
			   restaurantDisplayDto.setPhoneNumber(restaurantDto.getPhoneNumber());
			   restaurantDisplayDto.setOfferInPercentage(restaurantDto.getOfferInPercentage());
			   restaurantDisplayDto.setMinimumOrderAmount(restaurantDto.getMinimumOrderAmount());
			   Double distanceParticular = distance(lattitudeOfUser, longitudeOfUser, 
	                   restaurantDto.getLattitude(), restaurantDto.getLongitude());
			   restaurantDisplayDto.setDeliveryTime(distanceParticular * restaurantDto.getTimePerDistance());
			   restaurantDisplayDto.setFoods(restaurantDto.getFoods());
			   restaurantDisplayDto.setReviews(restaurantDto.getReviews());
			    for(RestaurantDisplayDto restDisplayDto : showRestaurants) {
				  if(restDisplayDto.getRestaurantId().equalsIgnoreCase(restaurantDto.getRestaurantId())) {  
				   flag2 = flag2 +1;
				  }
			    }
			   if(flag2 == 0) {
				   showRestaurants.add(restaurantDisplayDto);
			   }
		  }
		  return showRestaurants;
		  }	 
		else
		{
			throw new AvailibilityException("No Food available in your budget !!!");
		}
	}
	@Cacheable(value = "restaurant")
	@Override
	public List<RestaurantDisplayDto> getAllRestaurantsByGivingRating(Double rating, Double lattitudeOfUser,
			Double longitudeOfUser) throws DaoException {
		int flag = 0;
		List<RestaurantDisplayDto> showRestaurants = new ArrayList<>();
		List<RestaurantDto> restaurantDtos = new ArrayList<>();
		List<Restaurant> restaurants = restaurantRepository.findAll();
		
		for(Restaurant restaurant : restaurants)
		{
			if(restaurant.getRating() >= rating )
			{
				restaurantDtos.add(modelMapper.map(restaurant , RestaurantDto.class));
				flag = flag +1;
			}
		}
		if(flag != 0)
		{
			for(RestaurantDto restaurantDto : restaurantDtos)
			{
				RestaurantDisplayDto restaurantDisplayDto = new RestaurantDisplayDto();
				 restaurantDisplayDto.setRestaurantId(restaurantDto.getRestaurantId());
				   restaurantDisplayDto.setRestaurantName(restaurantDto.getRestaurantName());
				   restaurantDisplayDto.setRating(restaurantDto.getRating());
				   restaurantDisplayDto.setAddress(restaurantDto.getAddress());
				   restaurantDisplayDto.setPhoneNumber(restaurantDto.getPhoneNumber());
				   restaurantDisplayDto.setOfferInPercentage(restaurantDto.getOfferInPercentage());
				   restaurantDisplayDto.setMinimumOrderAmount(restaurantDto.getMinimumOrderAmount());
				   Double distanceParticular = distance(lattitudeOfUser, longitudeOfUser, 
		                   restaurantDto.getLattitude(), restaurantDto.getLongitude());
				   restaurantDisplayDto.setDeliveryTime(distanceParticular * restaurantDto.getTimePerDistance());
				   restaurantDisplayDto.setFoods(restaurantDto.getFoods());
				   restaurantDisplayDto.setReviews(restaurantDto.getReviews());
				   showRestaurants.add(restaurantDisplayDto);

			}
			 return showRestaurants;
		}
		 else
		 {
			 throw new AvailibilityException("No Restaurant available above this rating !!!");
		 }	
		
	}
	@Cacheable(value = "restaurant")
	@Override
	public List<RestaurantDisplayDto> getAllRestaurantsByGivingName(String restaurantName, Double lattitudeOfUser,
			Double longitudeOfUser) throws DaoException {
	
		int flag = 0;
		List<RestaurantDisplayDto> showRestaurants = new ArrayList<>();
		List<RestaurantDto> restaurantDtos = new ArrayList<>();
		List<Restaurant> restaurants = restaurantRepository.findAll();
		
		for(Restaurant restaurant : restaurants)
		{
			if(restaurant.getRestaurantName().equalsIgnoreCase(restaurantName))
			{
				restaurantDtos.add(modelMapper.map(restaurant , RestaurantDto.class));
				flag = flag +1;
			}
		}
		if(flag != 0)
		{
			for(RestaurantDto restaurantDto : restaurantDtos)
			{
				RestaurantDisplayDto restaurantDisplayDto = new RestaurantDisplayDto();
				 restaurantDisplayDto.setRestaurantId(restaurantDto.getRestaurantId());
				   restaurantDisplayDto.setRestaurantName(restaurantDto.getRestaurantName());
				   restaurantDisplayDto.setRating(restaurantDto.getRating());
				   restaurantDisplayDto.setAddress(restaurantDto.getAddress());
				   restaurantDisplayDto.setPhoneNumber(restaurantDto.getPhoneNumber());
				   restaurantDisplayDto.setOfferInPercentage(restaurantDto.getOfferInPercentage());
				   restaurantDisplayDto.setMinimumOrderAmount(restaurantDto.getMinimumOrderAmount());
				   Double distanceParticular = distance(lattitudeOfUser, longitudeOfUser, 
		                   restaurantDto.getLattitude(), restaurantDto.getLongitude());
				   restaurantDisplayDto.setDeliveryTime(distanceParticular * restaurantDto.getTimePerDistance());
				   restaurantDisplayDto.setFoods(restaurantDto.getFoods());
				   restaurantDisplayDto.setReviews(restaurantDto.getReviews());
				   showRestaurants.add(restaurantDisplayDto);

			}
			 return showRestaurants;
		}
		 else
		 {
			 throw new AvailibilityException("No Such Restaurant Available !!!");
		 }	
		
	}
	@Override
	public RestaurantDto getRestaurantDetailsFromRestaurantId(String restaurantId) throws DaoException {
		
		if(restaurantRepository.existsById(restaurantId))
		{
			return modelMapper.map(restaurantRepository.findById(restaurantId).get(), RestaurantDto.class);
		}
		else
		{
			throw new AvailibilityException("Such Restaurant Doesn't Exist !!!");
		}
	}

	@Override
	public String removeQuantity(String foodId, int quantity, String restaurantId) throws DaoException {
		Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
		List<Food> foodList = new ArrayList<>();
		foodList =restaurant.getFoods();
		List<Food> foodFinalList =  new ArrayList<>();
		for(Food food : foodList){
          if(food.getFoodId().equalsIgnoreCase(foodId)) {
        	  food.setQuantity(food.getQuantity() - quantity);
        	  if(food.getQuantity() < 0) {
        		 throw new AvailibilityException("Quantity Exceeded");
        	  }
        	  else {
        	  food.setRestaurant(restaurant);
        	  foodFinalList.add(food);
        	  }
          }
		}
		restaurant.setFoods(foodFinalList);
		restaurantRepository.save(restaurant);
		return "updated";
	}
			

	@Override
	public FoodDto checkFoodAvailibility(String foodId, String restaurantId) throws ServiceException  {
		
		Restaurant restaurant = restaurantRepository.findById(restaurantId).get();
		FoodDto foodRetrieved = new FoodDto();
		int flag =  0;
		for(Food food : restaurant.getFoods()){
			if(food.getFoodId().equalsIgnoreCase(foodId)){
				foodRetrieved = modelMapper.map(foodRepository.findById(food.getFoodId()).get() , FoodDto.class);
				flag = flag + 1 ;
			}
		}
		if(flag == 0){
			throw new AvailibilityException("No food available"); 
		}
		else{
			return foodRetrieved;
		}
	}

	@Scheduled(fixedRate = 600)
	public void evictAllcachesAtIntervals() {
	    evictAllCaches();
	}
	
	
	public void evictAllCaches() {
		 cacheManager.getCache("restaurant").clear();
		 
		
	}
}

	





