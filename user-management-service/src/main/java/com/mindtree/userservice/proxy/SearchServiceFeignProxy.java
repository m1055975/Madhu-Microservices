package com.mindtree.userservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mindtree.userservice.dto.ReviewDto;

@FeignClient(name = "search-management-service" , url = "52.188.137.142:9097" , fallback = SearchFallBack.class)
public interface SearchServiceFeignProxy {
	
	
	@RequestMapping(value = "/addReviewToRestaurant/{restaurantId}" , method = RequestMethod.POST)
	public ResponseEntity<ReviewDto> addReviewToRestaurant(@PathVariable String restaurantId , @RequestBody ReviewDto reviewDto);
	

}
