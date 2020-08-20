package com.mindtree.userservice.controller;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.mindtree.userservice.dto.ReviewDto;
import com.mindtree.userservice.dto.UserDto;
import com.mindtree.userservice.exception.UserServiceApplicationException;
import com.mindtree.userservice.service.UserService;

@SpringBootTest
public class UserControllerTest {
	
	
	@InjectMocks
	private UserController userController;
	
	@Mock
    private UserService userService;

	private UserDto userDto;
	
	private ReviewDto reviewDto;

	    @BeforeEach
	    void setUp() {
		MockitoAnnotations.initMocks(this);
		userDto = getUserDto();
		reviewDto = getReviewDto();
	}

private ReviewDto getReviewDto() {
			ReviewDto reviewDto1 = new ReviewDto();
			reviewDto1.setUserId("userId");
			reviewDto1.setRating(5.0);
			reviewDto1.setRestaurant(null);
			reviewDto1.setComment("comment");
			reviewDto1.setReviewId(1);
			return reviewDto1;
		}

private UserDto getUserDto() {
	UserDto userDto1 = new UserDto();
	userDto1.setAddress("address");
	userDto1.setEmail("email");
	userDto1.setFirstName("firstName");
	userDto1.setLastName("lastName");
	userDto1.setPhoneNumber(9337242327L);
	userDto1.setUserId("userId");
	return userDto1;
}
	    
        @Test
	    public void testAddNewUser() throws UserServiceApplicationException {
	    	when(userService.addNewUser(ArgumentMatchers.any() , ArgumentMatchers.any())).thenReturn(userDto);
	    	UserDto userAdded = userController.addNewUser(ArgumentMatchers.any(), ArgumentMatchers.any()).getBody();
	    	assertEquals(userDto , userAdded);
	    }
        
        @Test
        public void testGetUserDetails() throws UserServiceApplicationException {
        	when(userService.getUserDetails(ArgumentMatchers.anyString())).thenReturn(userDto);
        	UserDto userDto1 = userController.getUserDetails(ArgumentMatchers.anyString()).getBody();
        	assertEquals(userDto, userDto1);
        }
        @Test
        public void testAddReviewToRestaurant() throws UserServiceApplicationException {
        	when(userService.addReviewToRestaurant(ArgumentMatchers.anyString(), ArgumentMatchers.any())).thenReturn(reviewDto);
        	ReviewDto reviewDto1 = userController.addReviewToRestaurant(ArgumentMatchers.anyString(), ArgumentMatchers.any()).getBody();
        	assertEquals(reviewDto, reviewDto1);
        }

}
