package com.mindtree.orderservice.proxy;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mindtree.orderservice.dto.UserDto;
import com.mindtree.orderservice.exception.DaoException;


@FeignClient(name = "user-management-service", fallback = UserFallBack.class )
public interface UserServiceFeignProxy {
	
	@RequestMapping(value = "/getUserDetails/{userId}" , method = RequestMethod.GET) 
	public ResponseEntity<UserDto> getUserDetails(@PathVariable String userId) throws DaoException ;

}