package com.mindtree.searchservice.controller;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.mindtree.searchservice.controller.SearchController;
import com.mindtree.searchservice.dto.FoodDto;
import com.mindtree.searchservice.dto.LocationDto;
import com.mindtree.searchservice.dto.RestaurantDisplayDto;
import com.mindtree.searchservice.dto.RestaurantDto;
import com.mindtree.searchservice.dto.ReviewDto;
import com.mindtree.searchservice.exception.SearchServiceApplicationException;
import com.mindtree.searchservice.service.SearchService;

@SpringBootTest
public class SearchControllerTest {


	@InjectMocks
	private SearchController searchController;
	
	
	@Mock
    private SearchService searchService;


	private LocationDto locationDto;

	private RestaurantDto restaurantDto;

	private FoodDto foodDto;

	private ReviewDto reviewDto;

	private List<FoodDto> foodDtoLists;

	private List<ReviewDto> reviewDtoLists;

	private List<RestaurantDisplayDto> resturantDisplayList;

	    @BeforeEach
	    void setUp() {
		MockitoAnnotations.initMocks(this);
		locationDto = getLocationDto();
		restaurantDto = getRestaurantDto();
		foodDto = getFoodDto();
		reviewDto = getReviewDto();
		foodDtoLists = getFoodDtoList();
		reviewDtoLists = getReviewDtoList();
		resturantDisplayList = getResturantDisplayDto();
	}

	private LocationDto getLocationDto() {
		LocationDto locationDto1 = new LocationDto();
		locationDto1.setLocationId("1234.3");
		locationDto1.setLocationName("Lucknow");
		locationDto1.setRestaurants(null);
		return locationDto1;
	}

	private RestaurantDto getRestaurantDto() {
		
		RestaurantDto restaurantDto1 = new RestaurantDto();
		restaurantDto1.setRestaurantId("1234");
		restaurantDto1.setRestaurantName("Anpurnaa");
		restaurantDto1.setLattitude(1261.090);
		restaurantDto1.setLongitude(1725.22);
		restaurantDto1.setAddress("lucknow");
		restaurantDto1.setDeliveryPerson("ananr");
		restaurantDto1.setLocation(getLocationDto());
		return restaurantDto1;
	}

	private FoodDto getFoodDto() {
		FoodDto foodDto1 = new FoodDto();
		foodDto1.setFoodId("12345");
		foodDto1.setFoodName("maggi");
		foodDto1.setFoodPrice(100.67);
		foodDto1.setFoodType("breakfast");
		foodDto1.setQuantity(12);
		foodDto1.setRestaurant(null);
		return foodDto1;
	}

	private ReviewDto getReviewDto() {
		
		ReviewDto reviewDto1 = new ReviewDto();
		reviewDto1.setUserId("1712");
		reviewDto1.setReviewId(123);
		reviewDto1.setRating(8.5);
		reviewDto1.setComment("Good for fun and food");
		reviewDto1.setRestaurant(null);
		return reviewDto1;
	}

	private List<FoodDto> getFoodDtoList() {
		List<FoodDto> dtos = new ArrayList<>();
		FoodDto foodDto1 = new FoodDto();
		foodDto1.setFoodId("12345");
		foodDto1.setFoodName("maggi");
		foodDto1.setFoodPrice(100.67);
		foodDto1.setFoodType("breakfast");
		foodDto1.setQuantity(12);
		foodDto1.setRestaurant(restaurantDto);
		dtos.add(foodDto1);
		return dtos;
	}

	private List<ReviewDto> getReviewDtoList() {
		List<ReviewDto> reviewDtos = new ArrayList<>();
		ReviewDto reviewDto1 = new ReviewDto();
		reviewDto1.setUserId("1712");
		reviewDto1.setReviewId(123);
		reviewDto1.setRating(8.5);
		reviewDto1.setComment("Good for fun and food");
		reviewDto1.setRestaurant(restaurantDto);
		reviewDtos.add(reviewDto1);
		return reviewDtos;
	}

	private List<RestaurantDisplayDto> getResturantDisplayDto() {
		RestaurantDisplayDto restaurantDisplayDto1 = new RestaurantDisplayDto();
		restaurantDisplayDto1.setRestaurantId("1234");
		restaurantDisplayDto1.setRestaurantName("Anpurnaa");
		restaurantDisplayDto1.setFoods(foodDtoLists);
		restaurantDisplayDto1.setMinimumOrderAmount(1);
		restaurantDisplayDto1.setOfferInPercentage(23);
		restaurantDisplayDto1.setPhoneNumber("9648040823");
		restaurantDisplayDto1.setRating(8.7);
		restaurantDisplayDto1.setReviews(reviewDtoLists);
		List<RestaurantDisplayDto> restaurantDisplayDtos1 = new ArrayList<>();
		restaurantDisplayDtos1.add(restaurantDisplayDto1);
		return restaurantDisplayDtos1;

	}

	@Test
	void testAddLocationController() throws SearchServiceApplicationException {
		when(searchService.addLocation(ArgumentMatchers.any())).thenReturn(locationDto);
		LocationDto locationDtos = searchController.addLocation(locationDto).getBody();
		assertEquals(locationDto, locationDtos);
	}

	@Test
	void testAddRestaurantToLocation() throws SearchServiceApplicationException {
		when(searchService.addRestaurantToLocation(ArgumentMatchers.anyString(), ArgumentMatchers.any()))
				.thenReturn(restaurantDto);
		RestaurantDto restaurantDto2 = searchController
				.addRestaurantToLocation(ArgumentMatchers.anyString(), ArgumentMatchers.any()).getBody();
		assertEquals(restaurantDto, restaurantDto2);
	}

	@Test
	void testAddFoodToRestaurant() throws SearchServiceApplicationException {
		when(searchService.addFoodToRestaurant(ArgumentMatchers.anyString(), ArgumentMatchers.any())).thenReturn(foodDto);
		FoodDto foodDtos1 = searchController.addFoodToRestaurant(ArgumentMatchers.anyString(), ArgumentMatchers.any()).getBody();
		assertEquals(foodDto, foodDtos1);
	}

	@Test
	void testAddReviewToRestaurant() throws SearchServiceApplicationException {
		when(searchService.addReviewToRestaurant(ArgumentMatchers.anyString(), ArgumentMatchers.any()))
				.thenReturn(reviewDto);
		ReviewDto reviewDtos1 = searchController.addReviewToRestaurant(ArgumentMatchers.anyString(), ArgumentMatchers.any()).getBody();
		assertEquals(reviewDto, reviewDtos1);
	}

	@Test
	void testGetAllRestaurantsByGivingDistance() throws SearchServiceApplicationException {
		when(searchService.getAllRestaurantsByGivingDistance(12.89, 1242.89, 1768.90)).thenReturn(resturantDisplayList);
		List<RestaurantDisplayDto> restaurantDisplayDtos2 = searchController
				.getAllRestaurantsByGivingDistance(12.89, 1242.89, 1768.90).getBody();
		assertEquals(resturantDisplayList, restaurantDisplayDtos2);
	}

	@Test
	void testGetAllRestaurantsByGivingCuisineType () throws SearchServiceApplicationException
	{
		when(searchService.getAllRestaurantsByGivingCuisineType("Breakfast", 1242.89,1768.90)).thenReturn(resturantDisplayList);
		List<RestaurantDisplayDto> restaurantDisplay2 = searchController.getAllRestaurantsByGivingCuisineType("Breakfast",1242.89,1768.90).getBody();
		assertEquals(resturantDisplayList,restaurantDisplay2);
	}

	@Test
	void testGetAllRestaurantsByGivingBudget () throws SearchServiceApplicationException
	{
		when(searchService.getAllRestaurantsByGivingBudget(10000.0, 1242.89,1768.90)).thenReturn(resturantDisplayList);
		List<RestaurantDisplayDto> restaurantDisplay2 = searchController.getAllRestaurantsByGivingBudget(10000.0, 1242.89,1768.90).getBody();
		assertEquals(resturantDisplayList,restaurantDisplay2);
	}
	
	@Test
	void testGetAllRestaurantsByGivingRating () throws SearchServiceApplicationException
	{
		when(searchService.getAllRestaurantsByGivingRating(8.5, 1242.89,1768.90)).thenReturn(resturantDisplayList);
		List<RestaurantDisplayDto> restaurantDisplay2 = searchController.getAllRestaurantsByGivingRating(8.5, 1242.89,1768.90).getBody();
		assertEquals(resturantDisplayList,restaurantDisplay2);
	}
	
	@Test
	void testGetAllRestaurantsByGivingName () throws SearchServiceApplicationException
	{
		when(searchService.getAllRestaurantsByGivingName("Anpurnaa",1242.89,1768.90)).thenReturn(resturantDisplayList);
		List<RestaurantDisplayDto> restaurantDisplay2 = searchController.getAllRestaurantsByGivingName("Anpurnaa", 1242.89,1768.90).getBody();
		assertEquals(resturantDisplayList,restaurantDisplay2);
	}
	
	@Test
	void testGetRestaurantDetailsFromRestaurantId() throws SearchServiceApplicationException
	{
		when(searchService.getRestaurantDetailsFromRestaurantId(ArgumentMatchers.anyString())).thenReturn(restaurantDto);
		RestaurantDto restaurantDisplay2 = searchController.getRestaurantDetailsFromRestaurantId("1234").getBody();
		assertEquals(restaurantDto,restaurantDisplay2);
	}
	
	@Test
	void testCheckFoodAvailability() throws SearchServiceApplicationException
	{
		when(searchService.checkFoodAvailability(ArgumentMatchers.anyString(), ArgumentMatchers.anyString())).thenReturn(foodDto);
		FoodDto foodDto1 = searchController.checkFoodAvailability(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()).getBody();
		assertEquals(foodDto,foodDto1);
	}
	
	
}