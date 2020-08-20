package com.mindtree.userservice.controller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.mindtree.userservice.UserManagementServiceApplication;
import com.mindtree.userservice.dto.ReviewDto;
import com.mindtree.userservice.dto.UserDetailsDto;
import com.mindtree.userservice.dto.UserDto;
import com.mindtree.userservice.exception.UserServiceApplicationException;
import com.mindtree.userservice.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	private final Logger logger = LogManager.getLogger(UserController.class);
	
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = ResponseEntity.class),
			@ApiResponse(code = 500, message = "Error", response = UserManagementServiceApplication.class) })
	@ApiOperation(value = "This is used to add new user to the user Database.", produces = "application/json")
	
	@RequestMapping(value = "/addNewUser" , method = RequestMethod.POST)
	public ResponseEntity<UserDto> addNewUser(@RequestBody UserDetailsDto userDetailsDto ,@AuthenticationPrincipal OAuth2User oauth2User) throws UserServiceApplicationException{
		
		logger.info("Inside Method: "+ "adding new user" + ":\n");
		
		UserDto userAdded = userService.addNewUser(userDetailsDto ,oauth2User);
		return new ResponseEntity<>(userAdded ,HttpStatus.OK);
	}
	
	 
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = ResponseEntity.class),
			@ApiResponse(code = 500, message = "Error", response = UserManagementServiceApplication.class) })
    @ApiOperation(value = "This is used to fetch user details by giving userId", produces = "application/json")
    
    
	@RequestMapping(value = "/getUserDetails/{userId}" , method = RequestMethod.GET)
	public ResponseEntity<UserDto> getUserDetails(@PathVariable String userId) throws UserServiceApplicationException{
		
    	logger.info("Inside Method: "+ "Fetching user details from userId" + ":\n");
    	
		UserDto userRetrieved = userService.getUserDetails(userId);
		return new ResponseEntity<>(userRetrieved , HttpStatus.OK);
	}
    
    
    
    @ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = ResponseEntity.class),
			@ApiResponse(code = 500, message = "Error", response = UserManagementServiceApplication.class) })
	@ApiOperation(value = "This is used to add review to the restaurant.", produces = "application/json")
    
	
    @RequestMapping(value = "/addReviewToTheRestaurant/{restaurantId}" , method = RequestMethod.POST)
	public ResponseEntity<ReviewDto> addReviewToRestaurant( @PathVariable String restaurantId , @RequestBody ReviewDto reviewDto) throws UserServiceApplicationException{
		
    	logger.info("Inside Method: "+ "adding review to the restaurant after ordering" + ":\n");
    	
		ReviewDto reviewAdded = userService.addReviewToRestaurant(restaurantId ,reviewDto);
		return new ResponseEntity<>(reviewAdded , HttpStatus.OK);
	}
    

}
