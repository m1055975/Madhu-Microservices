package com.mindtree.orderservice.controller;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.mindtree.orderservice.OrderManagementServiceApplication;
import com.mindtree.orderservice.dto.CartFoodDto;
import com.mindtree.orderservice.dto.OrderDetailsDto;
import com.mindtree.orderservice.dto.OrderDto;
import com.mindtree.orderservice.dto.PaymentDto;
import com.mindtree.orderservice.exception.OrderManagementApplicationException;
import com.mindtree.orderservice.service.OrderService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	
	 private final Logger logger = LogManager.getLogger(OrderController.class);

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = ResponseEntity.class),
			@ApiResponse(code = 500, message = "Error", response = OrderManagementServiceApplication.class) })
	@ApiOperation(value = "This is used to make payment", produces = "application/json")

	@RequestMapping(value = "/processPayment" , method = RequestMethod.POST)
	public ResponseEntity<String> processPayment(@RequestBody PaymentDto paymentDto) throws OrderManagementApplicationException{
		
		logger.info("Inside Method: "+ "processing payment" + ":\n");
		
		String status = orderService.processPayment(paymentDto);
		return new ResponseEntity<>(status , HttpStatus.OK);

	}	

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = ResponseEntity.class),
				@ApiResponse(code = 500, message = "Error", response = OrderManagementServiceApplication.class) })
	@ApiOperation(value = "This is used to process the Order", produces = "application/json")
	
	
	@RequestMapping(value = "/placeOrder/{userId}/{restaurantId}/{paymentId}" , method = RequestMethod.POST)
	public ResponseEntity<OrderDto> placeOrder(@PathVariable String userId ,@PathVariable String restaurantId ,@PathVariable int paymentId , @RequestBody List<CartFoodDto> cartFoodList) throws OrderManagementApplicationException{
		
		logger.info("Inside Method: "+ "placing Order" + ":\n");
		
		OrderDto orderPlaced = orderService.placeOrder(userId , restaurantId ,paymentId, cartFoodList );
		return new ResponseEntity<>(orderPlaced , HttpStatus.OK);

  }
	

	@ApiResponses(value = { @ApiResponse(code = 200, message = "Success", response = ResponseEntity.class),
				@ApiResponse(code = 500, message = "Error", response = OrderManagementServiceApplication.class) })
	@ApiOperation(value = "This is used to get order details", produces = "application/json")

		
	@RequestMapping(value = "/getOrderDetails/{userId}" , method = RequestMethod.GET)
    public ResponseEntity<List<OrderDetailsDto>> getOrderDetails(@PathVariable String userId) throws OrderManagementApplicationException{
		
		logger.info("Inside Method: "+ "getting order details from userId" + ":\n");
		
		List<OrderDetailsDto> orderDetails = orderService.getOrderDetails(userId);
		return new ResponseEntity<>(orderDetails , HttpStatus.OK);
		
	}
	

}
