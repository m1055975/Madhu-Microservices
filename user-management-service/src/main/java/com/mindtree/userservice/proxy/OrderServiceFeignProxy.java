package com.mindtree.userservice.proxy;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mindtree.userservice.dto.OrderDetailsDto;

@FeignClient(name = "order-management-service" ,url = "52.188.137.142:9098" , fallback = OrderFallBack.class)
public interface OrderServiceFeignProxy {

	@RequestMapping(value = "/getOrderDetails/{userId}" , method = RequestMethod.GET)
    public ResponseEntity<List<OrderDetailsDto>> getOrderDetails(@PathVariable String userId);


}
