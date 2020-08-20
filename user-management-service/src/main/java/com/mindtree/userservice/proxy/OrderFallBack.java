package com.mindtree.userservice.proxy;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.mindtree.userservice.dto.OrderDetailsDto;

@Component
public class OrderFallBack implements OrderServiceFeignProxy {

	@Override
	public ResponseEntity<List<OrderDetailsDto>> getOrderDetails(String userId) {
	OrderDetailsDto orderDetailsDto = new OrderDetailsDto();
	orderDetailsDto.setUserId("fallback");
	List<OrderDetailsDto> orderDetailsDtos = new ArrayList<>();
	orderDetailsDtos.add(orderDetailsDto);
		return new ResponseEntity<>(orderDetailsDtos , HttpStatus.NOT_FOUND);
	}

}
