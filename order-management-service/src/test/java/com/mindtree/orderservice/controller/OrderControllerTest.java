package com.mindtree.orderservice.controller;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import com.mindtree.orderservice.controller.OrderController;
import com.mindtree.orderservice.dto.OrderDto;
import com.mindtree.orderservice.exception.OrderManagementApplicationException;
import com.mindtree.orderservice.service.OrderService;

@SpringBootTest
public class OrderControllerTest {
	
	@InjectMocks
	private OrderController orderController;
	
	@Mock
	private OrderService orderService;
	
	private String result;
	
	private OrderDto orderDto;
	
	
	 @BeforeEach
	    void setUp() {
		MockitoAnnotations.initMocks(this);
		result = getResult();
		orderDto = getOrderDto();
	}

	
	private OrderDto getOrderDto() {
		OrderDto orderDto1 = new OrderDto();
		orderDto1.setUserId("userId");
		orderDto1.setDeliveryPerson("deliveryPerson");
		orderDto1.setOrderId(1);
		orderDto1.setRestaurantId("restaurantId");
		orderDto1.setRestaurantName("restaurantName");
		orderDto1.setRestaurantAddress("restaurantAddress");
		orderDto1.setTax(5);
		return orderDto1;
	}


	private String getResult() {
		String result1 = "result";
		return result1;
	}


	@Test
	 public void testprocessPayment() throws OrderManagementApplicationException
	 {
		 when(orderService.processPayment(ArgumentMatchers.any())).thenReturn(result);
		 String result1 = orderController.processPayment(ArgumentMatchers.any()).getBody();
		 assertEquals(result, result1);
		 
	 }
	
	@Test
	public void testPlaceOrder() throws OrderManagementApplicationException
	{
		when(orderService.placeOrder(ArgumentMatchers.anyString() , ArgumentMatchers.anyString() , ArgumentMatchers.anyInt() , ArgumentMatchers.anyList())).thenReturn(orderDto);
		OrderDto orderDto1 = orderController.placeOrder(ArgumentMatchers.anyString() , ArgumentMatchers.anyString() , ArgumentMatchers.anyInt() , ArgumentMatchers.anyList()).getBody();
		assertEquals(orderDto, orderDto1);
	}
	

}
