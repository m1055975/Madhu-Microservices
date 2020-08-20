package com.mindtree.orderservice.dao.impl;
import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import com.mindtree.orderservice.dao.OrderDao;
import com.mindtree.orderservice.dto.CartFoodDto;
import com.mindtree.orderservice.dto.FoodDto;
import com.mindtree.orderservice.dto.MessageDto;
import com.mindtree.orderservice.dto.OrderDetailsDto;
import com.mindtree.orderservice.dto.OrderDto;
import com.mindtree.orderservice.dto.PaymentDto;
import com.mindtree.orderservice.dto.RestaurantDto;
import com.mindtree.orderservice.dto.UserDto;
import com.mindtree.orderservice.entity.Orders;
import com.mindtree.orderservice.entity.Payment;
import com.mindtree.orderservice.exception.AvailibilityException;
import com.mindtree.orderservice.exception.DaoException;
import com.mindtree.orderservice.exception.HystrixFallBackException;
import com.mindtree.orderservice.proxy.SearchServiceFeignProxy;
import com.mindtree.orderservice.proxy.UserServiceFeignProxy;
import com.mindtree.orderservice.repository.OrderRepository;
import com.mindtree.orderservice.repository.PaymentRepository;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
//import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;

import feign.FeignException;


@Service
public class OrderDaoImplementation implements OrderDao {
	
	
	@Autowired
	private UserServiceFeignProxy userServiceFeignProxy;
	
	@Autowired
	private SearchServiceFeignProxy searchServiceFeignProxy;
	
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private OrderRepository orderRepository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;
  
    private ModelMapper modelMapper = new ModelMapper();
    
	
    private static final String TOPIC = "order";
    
//        @HystrixCommand(fallbackMethod =  "orderDetailsFallBack",
//            commandProperties = {
//                     @HystrixProperty(name ="execution.isolation.thread.timeoutInMilliseconds" , value = "1000"),
//                     @HystrixProperty(name ="circuitBreaker.requestVolumeThreshold" , value = "6"),
//                     @HystrixProperty(name ="circuitBreaker.errorThresholdPercentage" , value = "50"),
//                     @HystrixProperty(name ="circuitBreaker.sleepWindowInMilliseconds" , value = "5000")
//                })
        @Override
	    public OrderDto placeOrder(String userId, String restaurantId, int paymentId , List<CartFoodDto> cartFoodList) throws DaoException {
		OrderDto orderDto = new OrderDto();
		UserDto userOrdered = new UserDto();
		RestaurantDto restaurantDetails = new RestaurantDto();
		List<FoodDto> foodDtoList = new ArrayList<>();
		if(paymentRepository.existsById(paymentId)){
		try {	
		userOrdered = userServiceFeignProxy.getUserDetails(userId).getBody();
		}
		catch(FeignException e) {
			throw new DaoException(e.contentUTF8());
		}		if(userOrdered.getUserId().equalsIgnoreCase("fallback")) {
			throw new HystrixFallBackException("Hystrix FallBack !!! User service is Down at this moment !!!");
		}
		try {
         restaurantDetails = searchServiceFeignProxy.getRestaurantDetailsFromRestaurantId(restaurantId).getBody();
		}
		catch (FeignException e) {
			throw new DaoException(e.contentUTF8());
		}
		if(restaurantDetails.getRestaurantId().equalsIgnoreCase("fallback")) {
			throw new HystrixFallBackException("Hystrix FallBack !!! Restaurant service is Down !!!");
		}
		for(CartFoodDto cartFood : cartFoodList){
			FoodDto foodDto = new FoodDto();
			try {
			foodDto =searchServiceFeignProxy.checkFoodAvailability(cartFood.getFoodId() ,restaurantId).getBody();
			}
			catch (FeignException e) {
				throw new DaoException(e.contentUTF8());
			}
		    foodDto.setQuantity(cartFood.getQuantity());
			foodDtoList.add(foodDto);
		}
		Double totalPrice = 0.0;
		Orders orderPlaced = new Orders();
		orderPlaced.setUserId(userId);
		orderPlaced.setUserName(userOrdered.getFirstName() +" " + userOrdered.getLastName());
		orderPlaced.setRestaurantId(restaurantId);
		orderPlaced.setRestaurantName(restaurantDetails.getRestaurantName());
		orderPlaced.setRestaurantAddress(restaurantDetails.getAddress());
		orderPlaced.setDeliveryPerson(restaurantDetails.getDeliveryPerson());
		orderPlaced.setDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
		for(FoodDto foodDto1 : foodDtoList )
		{
			totalPrice = totalPrice + (foodDto1.getFoodPrice() * foodDto1.getQuantity());
			try {
			searchServiceFeignProxy.removeQuantity(foodDto1.getFoodId(), foodDto1.getQuantity(), restaurantId).getBody();
			}
			catch(FeignException e) {
				throw new DaoException(e.contentUTF8());
			}
		totalPrice = totalPrice + ((totalPrice * 5.8)/100);	
		if(totalPrice < restaurantDetails.getMinimumOrderAmount()) {
			throw new AvailibilityException("Minimum order Amount is :"+ restaurantDetails.getMinimumOrderAmount() +" " +"Your total order amount is " +totalPrice);
		}
		orderPlaced.setTotalPrice(totalPrice);
		orderRepository.save(orderPlaced);
		orderDto = modelMapper.map(orderPlaced, OrderDto.class);
		orderDto.setTax(5.8);
		orderDto.setFoods(foodDtoList);
		}
	}
	
		else{
			throw new AvailibilityException("Please complete your payment !!!");
		}
		MessageDto messageDto = new MessageDto();
		messageDto.setUserId(orderDto.getUserId());
		messageDto.setUserName(orderDto.getUserName());
		messageDto.setOrderId(orderDto.getOrderId());
		messageDto.setRestaurantName(orderDto.getRestaurantName());
		messageDto.setTotalAmount(orderDto.getTotalPrice());
		messageDto.setMessage("ORDER PLACED !!! THANK YOU ");
		
		kafkaTemplate.send(TOPIC, messageDto);
		
		
		return orderDto;
	}
	

	@Override
	public String processPayment(PaymentDto paymentDto) {
		Payment payment = modelMapper.map(paymentDto, Payment.class);
		payment.setNameOnCard(paymentDto.getNameOnCard());
		payment.setPaymentStatus(true);
		paymentRepository.save(payment);
		return "payment SuccessFull";
	}


	@Override
	public List<OrderDetailsDto> getOrderDetails(String userId) throws DaoException {
		List<OrderDetailsDto> orderDetails = new ArrayList<>();
		List<Orders> orderList = orderRepository.findAll();
		int flag =0;
		for(Orders order : orderList) {
			if(order.getUserId().equalsIgnoreCase(userId)) {
			   orderDetails.add(modelMapper.map(order, OrderDetailsDto.class));
			   flag = flag +1 ;
			}
		}
		if(flag == 0){
			OrderDetailsDto orderDetailsDto = new OrderDetailsDto();
			orderDetailsDto.setUserId("noOrder");
			orderDetails.add(orderDetailsDto);
			return orderDetails;
		}else {
			
		return orderDetails;
		}
	}
	
//	@SuppressWarnings("unused")
//	private OrderDto orderDetailsFallBack(String userId, String restaurantId, int paymentId , List<CartFoodDto> cartFoodList){
//		OrderDto orderDto = new OrderDto();
//		orderDto.setUserId(userId);
//		orderDto.setRestaurantId(restaurantId);
//		orderDto.setOrderId(0);
//		orderDto.setFoods(null);
//		orderDto.setTax(0);
//		orderDto.setRestaurantName("service Down !! Sorry for inconvenience");
//		orderDto.setTotalPrice(0);
//		orderDto.setUserName("service Down !! sorry for inconvenience");
//		orderDto.setDate(java.sql.Date.valueOf(java.time.LocalDate.now()));
//		return orderDto;
//		
//		
//		
//	}
	
		

}
