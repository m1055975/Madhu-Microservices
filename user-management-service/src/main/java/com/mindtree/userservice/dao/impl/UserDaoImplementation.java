package com.mindtree.userservice.dao.impl;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import com.mindtree.userservice.dao.UserDao;
import com.mindtree.userservice.dto.OrderDetailsDto;
import com.mindtree.userservice.dto.ReviewDto;
import com.mindtree.userservice.dto.UserDetailsDto;
import com.mindtree.userservice.dto.UserDto;
import com.mindtree.userservice.entity.User;
import com.mindtree.userservice.exception.AvailibilityException;
import com.mindtree.userservice.exception.DaoException;
import com.mindtree.userservice.exception.HystrixFallBackException;
import com.mindtree.userservice.proxy.OrderServiceFeignProxy;
import com.mindtree.userservice.proxy.SearchServiceFeignProxy;
import com.mindtree.userservice.repository.UserRepository;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import feign.FeignException;

@Service
public class UserDaoImplementation implements UserDao {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private OrderServiceFeignProxy orderServiceFeignProxy;
	
	@Autowired
	private SearchServiceFeignProxy searchServiceFeignProxy;
	
	
	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public UserDto addNewUser(UserDetailsDto userDetailsDto, OAuth2User oauth2User) throws DaoException {
		if(!userRepository.existsById(userDetailsDto.getUserId())){
			User user = new User();
			user.setUserId(userDetailsDto.getUserId());
			user.setEmail(oauth2User.getAttribute("email"));
			user.setFirstName(oauth2User.getAttribute("given_name"));
			user.setLastName(oauth2User.getAttribute("family_name"));
			user.setAddress(userDetailsDto.getAddress());
			user.setPhoneNumber(userDetailsDto.getPhoneNumber());
			return modelMapper.map(userRepository.save(user), UserDto.class);
		}
		else
		{
			throw new AvailibilityException("User Already Present !!!");
		}
	}

	@Override
	public UserDto getUserDetails(String userId) throws DaoException {
		if(userRepository.existsById(userId)){
			return modelMapper.map(userRepository.findById(userId).get(), UserDto.class);
		}
		else {
			throw new AvailibilityException("User Not Available !!!");
		}
	}

//	 @HystrixCommand(fallbackMethod =  "reviewFallBack",
//	            commandProperties = {
//	                     @HystrixProperty(name ="execution.isolation.thread.timeoutInMilliseconds" , value = "10000"),
//	                     @HystrixProperty(name ="circuitBreaker.requestVolumeThreshold" , value = "6"),
//	                     @HystrixProperty(name ="circuitBreaker.errorThresholdPercentage" , value = "50"),
//	                     @HystrixProperty(name ="circuitBreaker.sleepWindowInMilliseconds" , value = "5000")
//	                })
	@Override
	public ReviewDto addReviewToRestaurant(String restaurantId , ReviewDto reviewDto) throws DaoException {
		 
		 List<OrderDetailsDto> orderDetails = new ArrayList<>();
		 ReviewDto reviewAdded = new ReviewDto();
        try {
		orderDetails = orderServiceFeignProxy.getOrderDetails(reviewDto.getUserId()).getBody();
        }
        catch (FeignException e) {
			throw new DaoException(e.contentUTF8());
		}
		if(orderDetails.get(0).getRestaurantId().equalsIgnoreCase("fallback")){
			throw new HystrixFallBackException("Order Service is Down at this moment !!!");
		}
		try {
		reviewAdded = searchServiceFeignProxy.addReviewToRestaurant(restaurantId, reviewDto).getBody();
		}
		catch (FeignException e) {
			throw new DaoException(e.contentUTF8());
		}
		
		if(reviewAdded.getUserId().equalsIgnoreCase("fallBack")) {
			throw new HystrixFallBackException("Search Service is currently Down !!!");
		}
		return reviewAdded;
	}
	 
//	 @SuppressWarnings("unused")
//	private ReviewDto reviewFallBack(String restaurantId , ReviewDto reviewDto) {
//		 ReviewDto reviewDto1 = new ReviewDto();
//		 reviewDto1.setReviewId(0);
//		 reviewDto1.setRating(reviewDto.getRating());
//		 reviewDto1.setComment("Service Down !!! Sorry for inconvenience");
//		 reviewDto1.setUserId(reviewDto.getUserId());
//		 return reviewDto1;
//		 
//	 }


}
