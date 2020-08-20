package com.mindtree.orderservice.proxy;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.mindtree.orderservice.dto.FoodDto;
import com.mindtree.orderservice.dto.RestaurantDto;

@Component
public class RestaurantFallBack implements SearchServiceFeignProxy {

	@Override
	public ResponseEntity<RestaurantDto> getRestaurantDetailsFromRestaurantId(String restaurantId) {
		RestaurantDto restaurantDto = new RestaurantDto();
		restaurantDto.setRestaurantId("fallBack");
		return new ResponseEntity<>(restaurantDto , HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<String> removeQuantity(String foodId, int quantity, String restaurantId) {
	
		return new ResponseEntity<>("fallback" , HttpStatus.NOT_FOUND);
	}

	@Override
	public ResponseEntity<FoodDto> checkFoodAvailability(String foodId, String restaurantId) {
		
		FoodDto foodDto = new FoodDto();
		foodDto.setFoodId("fallback");
		return new ResponseEntity<>(foodDto ,HttpStatus.NOT_FOUND);
	}

	
	
	
}
