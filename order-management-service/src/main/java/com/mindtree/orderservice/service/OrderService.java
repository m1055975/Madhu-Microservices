package com.mindtree.orderservice.service;
import java.util.List;
import com.mindtree.orderservice.dto.CartFoodDto;
import com.mindtree.orderservice.dto.OrderDetailsDto;
import com.mindtree.orderservice.dto.OrderDto;
import com.mindtree.orderservice.dto.PaymentDto;
import com.mindtree.orderservice.exception.ServiceException;

public interface OrderService {

	OrderDto placeOrder(String userId, String restaurantId, int paymentId, List<CartFoodDto> cartFoodList) throws ServiceException;

	String processPayment(PaymentDto paymentDto) throws ServiceException;

	List<OrderDetailsDto> getOrderDetails(String userId) throws ServiceException;



}
