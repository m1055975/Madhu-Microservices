package com.mindtree.orderservice.service.impl;
import java.util.List;
import com.mindtree.orderservice.dto.CartFoodDto;
import com.mindtree.orderservice.dto.OrderDetailsDto;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mindtree.orderservice.dao.OrderDao;
import com.mindtree.orderservice.dto.OrderDto;
import com.mindtree.orderservice.dto.PaymentDto;
import com.mindtree.orderservice.exception.DaoException;
import com.mindtree.orderservice.exception.ServiceException;
import com.mindtree.orderservice.exception.ValidationException;
import com.mindtree.orderservice.service.OrderService;

@Service
public class OrderServiceImplementation implements OrderService {
	
	@Autowired
	private OrderDao orderDao;
	
	 private final Logger logger = LogManager.getLogger(OrderServiceImplementation.class);

	@Override
	public OrderDto placeOrder(String userId, String restaurantId,int paymentId , List<CartFoodDto> cartFoodList) throws ServiceException {
		if(restaurantId.length() != 10){
		   throw new ValidationException("Restaurant Id is Incorrect ! (Length = 10)");
		 }
		if(userId.length() != 10){
		 throw new ValidationException("User Id Invalid !!! (length = 10 )");
		}
        for(CartFoodDto cartFood : cartFoodList) {
				if(cartFood.getQuantity() <= 0) {
					throw new ValidationException("Quantity can't be zero or Negative :" + cartFood.getFoodId());
				}
			}
			try{
			return orderDao.placeOrder(userId ,restaurantId ,paymentId , cartFoodList);
			}
			catch(DaoException e){
				logger.error(ExceptionUtils.getFullStackTrace(e));
				throw new ServiceException(e.getMessage());
			}
		}
	

	@Override
	public String processPayment(PaymentDto paymentDto) throws ServiceException {
		
		if(paymentDto.getDebitCardNo().length() != 12){
			throw new ValidationException("Debit card number should be 12 digit long");
		}
		 if(paymentDto.getCvv().length() != 3){
			throw new ValidationException("CVV should be 3 digit long");
		 }
		 if(paymentDto.getExpiryMonth().length() != 2) {
			 throw new ValidationException("Month should be 2 digits long");
		 }
		 if(paymentDto.getExpiryYear().length() != 4) {
			 throw new ValidationException("Year should be 4 digit long");
		 }
		 return orderDao.processPayment(paymentDto);
		 }

	@Override
	public List<OrderDetailsDto> getOrderDetails(String userId) throws ServiceException {
		if(userId.length() != 10) {
			throw new ValidationException("User Id Invalid (Length = 10)");
		}
		else {
			return orderDao.getOrderDetails(userId);
			
		}
	}


		 
}
