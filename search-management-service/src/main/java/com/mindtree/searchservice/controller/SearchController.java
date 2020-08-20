package com.mindtree.searchservice.controller;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.mindtree.searchservice.SearchManagementServiceApplication;
import com.mindtree.searchservice.dto.FoodDto;
import com.mindtree.searchservice.dto.LocationDto;
import com.mindtree.searchservice.dto.RestaurantDisplayDto;
import com.mindtree.searchservice.dto.RestaurantDto;
import com.mindtree.searchservice.dto.ReviewDto;
import com.mindtree.searchservice.exception.SearchServiceApplicationException;
import com.mindtree.searchservice.service.SearchService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class SearchController {
	
	@Autowired
	private SearchService searchService;
	
	 private final Logger logger = LogManager.getLogger(SearchController.class);
	

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = ResponseEntity.class),
			@ApiResponse(code = 500, message = "Error", response = SearchManagementServiceApplication.class) })
	@ApiOperation(value = "This is used to add new Locations to the Booking site", produces = "application/json")
	
	@RequestMapping(value = "/addNewLocationToSite" , method = RequestMethod.POST)
	public ResponseEntity<LocationDto> addLocation(@RequestBody LocationDto locationDto) throws SearchServiceApplicationException{
		
		logger.info("Inside Method: "+ "adding New Location to the website" + ":\n");
		
		LocationDto locationAdded = searchService.addLocation(locationDto);
		return new ResponseEntity<>(locationAdded ,HttpStatus.OK);
	}
	
	
	
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = ResponseEntity.class),
			@ApiResponse(code = 500, message = "Error", response = SearchManagementServiceApplication.class) })
	@ApiOperation(value = "This is used to add new Restaurant to the Location", produces = "application/json")
	
	@RequestMapping(value = "/addRestaurantToLocation/{locationId}" , method = RequestMethod.POST)
	public ResponseEntity<RestaurantDto> addRestaurantToLocation(@PathVariable String locationId , @RequestBody RestaurantDto restaurantDto) throws SearchServiceApplicationException{
		
		logger.info("Inside Method: "+ "adding new Restaurant to a particular location" + ":\n");
		
		RestaurantDto restaurantAdded = searchService.addRestaurantToLocation(locationId , restaurantDto);
		return new ResponseEntity<>(restaurantAdded , HttpStatus.OK);
		
	}
	
	
	
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = ResponseEntity.class),
			@ApiResponse(code = 500, message = "Error", response = SearchManagementServiceApplication.class) })
	@ApiOperation(value = "This is used to add new Food to the Restaurant", produces = "application/json")
	
	@RequestMapping(value = "/addFoodToRestaurant/{restaurantId}" , method = RequestMethod.POST)
	public ResponseEntity<FoodDto> addFoodToRestaurant(@PathVariable String restaurantId,@RequestBody FoodDto foodDto) throws SearchServiceApplicationException {
	
		logger.info("Inside Method: "+ "adding food Item to particular restaurant" + ":\n");
		
		FoodDto foodAdded = searchService.addFoodToRestaurant(restaurantId, foodDto);
		return new ResponseEntity<>(foodAdded,HttpStatus.OK);
		
	}
	
	
	
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = ResponseEntity.class),
			@ApiResponse(code = 500, message = "Error", response = SearchManagementServiceApplication.class) })
	@ApiOperation(value = "This is used to add a review to the Restaurant", produces = "application/json")
	
	@RequestMapping(value = "/addReviewToRestaurant/{restaurantId}" , method = RequestMethod.POST)
	public ResponseEntity<ReviewDto> addReviewToRestaurant(@PathVariable String restaurantId , @RequestBody ReviewDto reviewDto) throws SearchServiceApplicationException{
		
		logger.info("Inside Method: "+ "adding review to the restaurant" + ":\n");
		
		ReviewDto reviewAdded = searchService.addReviewToRestaurant(restaurantId , reviewDto);
		return new ResponseEntity<>(reviewAdded , HttpStatus.OK);
		
	}
	
	
	
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = ResponseEntity.class),
			@ApiResponse(code = 500, message = "Error", response = SearchManagementServiceApplication.class) })
	@ApiOperation(value = "This is used to get Restaurant Details by giving the distance", produces = "application/json")
	
	@RequestMapping(value = "/getAllRestaurntsByDistance/{distance}/{lattitudeOfUser}/{longitudeOfUser}" , method = RequestMethod.GET)
	public ResponseEntity<List<RestaurantDisplayDto>> getAllRestaurantsByGivingDistance(@PathVariable Double distance ,@PathVariable Double lattitudeOfUser ,@PathVariable Double longitudeOfUser) throws SearchServiceApplicationException{
		
		logger.info("Inside Method: "+ "fetching restaurant Details By giving distance" + ":\n");
		
		List<RestaurantDisplayDto> restaurantsByDistance = searchService.getAllRestaurantsByGivingDistance(distance , lattitudeOfUser , longitudeOfUser);
		return new ResponseEntity<>(restaurantsByDistance , HttpStatus.OK);
	}
	
	
	
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = ResponseEntity.class),
			@ApiResponse(code = 500, message = "Error", response = SearchManagementServiceApplication.class) })
	@ApiOperation(value = "This is used to get Restaurant Details by giving the food cuisine type", produces = "application/json")
	
	@RequestMapping(value = "/getAllRestaurntsByCuisineType/{foodType}/{lattitudeOfUser}/{longitudeOfUser}" , method = RequestMethod.GET)
	public ResponseEntity<List<RestaurantDisplayDto>> getAllRestaurantsByGivingCuisineType(@PathVariable String foodType , @PathVariable Double lattitudeOfUser , @PathVariable Double longitudeOfUser) throws SearchServiceApplicationException{
		
		logger.info("Inside Method: "+ "fetching restaurant details by giving cuisine type" + ":\n");
		
		List<RestaurantDisplayDto> restaurantsByFoodType = searchService.getAllRestaurantsByGivingCuisineType(foodType , lattitudeOfUser , longitudeOfUser);
		return new ResponseEntity<>(restaurantsByFoodType , HttpStatus.OK);
	}
	
	
	
	
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = ResponseEntity.class),
			@ApiResponse(code = 500, message = "Error", response = SearchManagementServiceApplication.class) })
	@ApiOperation(value = "This is used to get Restaurant Details by giving the Budget", produces = "application/json")
	
	@RequestMapping(value = "/getAllRestaurntsByBudget/{budget}/{lattitudeOfUser}/{longitudeOfUser}" , method = RequestMethod.GET)
	public ResponseEntity<List<RestaurantDisplayDto>> getAllRestaurantsByGivingBudget(@PathVariable Double budget, @PathVariable Double lattitudeOfUser , @PathVariable Double longitudeOfUser) throws SearchServiceApplicationException{
		
		logger.info("Inside Method: "+ "fetching restaurant details by giving the budget" + ":\n");
		
		List<RestaurantDisplayDto> restaurantsByBudget = searchService.getAllRestaurantsByGivingBudget(budget, lattitudeOfUser , longitudeOfUser);
		return new ResponseEntity<>(restaurantsByBudget , HttpStatus.OK);
	}
	
	
	

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = ResponseEntity.class),
			@ApiResponse(code = 500, message = "Error", response = SearchManagementServiceApplication.class) })
	@ApiOperation(value = "This is used to get Restaurant Details by giving the rating", produces = "application/json")
	
	@RequestMapping(value = "/getAllRestaurntsByRating/{rating}/{lattitudeOfUser}/{longitudeOfUser}" , method = RequestMethod.GET)
	public ResponseEntity<List<RestaurantDisplayDto>> getAllRestaurantsByGivingRating(@PathVariable Double rating, @PathVariable  Double lattitudeOfUser , @PathVariable Double longitudeOfUser) throws SearchServiceApplicationException{
		
		logger.info("Inside Method: "+ "fetching restaurant details by giving rating" + ":\n");
		
		List<RestaurantDisplayDto> restaurantsByRating = searchService.getAllRestaurantsByGivingRating(rating, lattitudeOfUser , longitudeOfUser);
		return new ResponseEntity<>(restaurantsByRating , HttpStatus.OK);
	}
	
	
	
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = ResponseEntity.class),
			@ApiResponse(code = 500, message = "Error", response = SearchManagementServiceApplication.class) })
	@ApiOperation(value = "This is used to get Restaurant Details by giving the Restaurant Name", produces = "application/json")
	
	@RequestMapping(value = "/getAllRestaurntsByName/{restaurantName}/{lattitudeOfUser}/{longitudeOfUser}" , method = RequestMethod.GET)
	public ResponseEntity<List<RestaurantDisplayDto>> getAllRestaurantsByGivingName(@PathVariable String restaurantName, @PathVariable Double lattitudeOfUser , @PathVariable Double longitudeOfUser) throws SearchServiceApplicationException{
		
		logger.info("Inside Method: "+ "fetching restaurant details by giving restaurant name" + ":\n");
		
		List<RestaurantDisplayDto> restaurantsByName = searchService.getAllRestaurantsByGivingName(restaurantName, lattitudeOfUser , longitudeOfUser);	
		return new ResponseEntity<>(restaurantsByName , HttpStatus.OK);
  }
	
	

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = ResponseEntity.class),
			@ApiResponse(code = 500, message = "Error", response = SearchManagementServiceApplication.class) })
	@ApiOperation(value = "This is used to get Restaurant Details by giving Restaurant Id", produces = "application/json")
	
	@RequestMapping(value = "/getRestaurantDetails/{restaurantId}" , method = RequestMethod.GET)
	public ResponseEntity<RestaurantDto> getRestaurantDetailsFromRestaurantId(@PathVariable String restaurantId) throws SearchServiceApplicationException{
		
		logger.info("Inside Method: "+ "fetching restaurant details by giving restaurant Id" + ":\n");
		
		RestaurantDto restaurantFetched = searchService.getRestaurantDetailsFromRestaurantId(restaurantId);
		return new ResponseEntity<>(restaurantFetched , HttpStatus.OK);
	}
	
	
	
	
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = ResponseEntity.class),
			@ApiResponse(code = 500, message = "Error", response = SearchManagementServiceApplication.class) })
	@ApiOperation(value = "This is used to Update the Quantity of food after processing order", produces = "application/json")

	
	@RequestMapping(value = "/quantityUpdated/{foodId}/{quantity}/{restaurantId}" , method = RequestMethod.PUT)
	public ResponseEntity<String> removeQuantity(@PathVariable String foodId , @PathVariable int quantity, @PathVariable String restaurantId) throws SearchServiceApplicationException{
		
		logger.info("Inside Method: "+ "updating the food quantity after processing the order" + ":\n");
		
		String response = searchService.removeQuantity(foodId , quantity , restaurantId);
		return new ResponseEntity<>(response , HttpStatus.OK);
		
	}
	
	
	
	
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = ResponseEntity.class),
			@ApiResponse(code = 500, message = "Error", response = SearchManagementServiceApplication.class) })
	@ApiOperation(value = "This is used to check whether food is present or not", produces = "application/json")

	@RequestMapping(value = "/checkFoodAvailibility/{foodId}/{restaurantId}" , method = RequestMethod.GET)
	public ResponseEntity<FoodDto> checkFoodAvailability(@PathVariable String foodId,@PathVariable String restaurantId) throws SearchServiceApplicationException{
		
		logger.info("Inside Method: "+ "checking whether food is available in the restaurant" + ":\n");
		
		return new ResponseEntity<>(searchService.checkFoodAvailability(foodId , restaurantId) , HttpStatus.OK);
		
	
	}
}
	  
	
	
	

	
	
	
	


	
