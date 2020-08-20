package com.mindtree.orderservice.dao;
import java.util.List;
import com.mindtree.orderservice.dto.CartFoodDto;
import com.mindtree.orderservice.dto.OrderDetailsDto;
import com.mindtree.orderservice.dto.OrderDto;
import com.mindtree.orderservice.dto.PaymentDto;
import com.mindtree.orderservice.exception.DaoException;

public interface OrderDao {

	OrderDto placeOrder(String userId, String restaurantId, int paymentId, List<CartFoodDto> cartFoodList) throws DaoException;

	String processPayment(PaymentDto paymentDto);

	List<OrderDetailsDto> getOrderDetails(String userId) throws DaoException;



}
