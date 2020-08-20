package com.mindtree.orderservice.proxy;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mindtree.orderservice.dto.FoodDto;
import com.mindtree.orderservice.dto.RestaurantDto;

@FeignClient(name = "search-management-service", fallback = RestaurantFallBack.class )
public interface SearchServiceFeignProxy {
	
	@RequestMapping(value = "/getRestaurantDetails/{restaurantId}" , method = RequestMethod.GET)
	public ResponseEntity<RestaurantDto> getRestaurantDetailsFromRestaurantId(@PathVariable String restaurantId);
    
	@RequestMapping(value = "/quantityUpdated/{foodId}/{quantity}/{restaurantId}" , method = RequestMethod.PUT)
	public ResponseEntity<String> removeQuantity(@PathVariable String foodId , @PathVariable int quantity, @PathVariable String restaurantId);

	@RequestMapping(value = "/checkFoodAvailibility/{foodId}/{restaurantId}" , method = RequestMethod.GET)
	public ResponseEntity<FoodDto> checkFoodAvailability(@PathVariable String foodId,@PathVariable String restaurantId);
		
}
