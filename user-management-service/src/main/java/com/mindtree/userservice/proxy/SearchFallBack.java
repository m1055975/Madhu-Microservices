package com.mindtree.userservice.proxy;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.mindtree.userservice.dto.ReviewDto;

@Component
public class SearchFallBack implements SearchServiceFeignProxy {

	@Override
	public ResponseEntity<ReviewDto> addReviewToRestaurant(String restaurantId, ReviewDto reviewDto) {
		ReviewDto reviewDto2 = new ReviewDto();
		reviewDto2.setUserId("fallback");
		return new ResponseEntity<>(reviewDto2 ,HttpStatus.NOT_FOUND);
	}

}
