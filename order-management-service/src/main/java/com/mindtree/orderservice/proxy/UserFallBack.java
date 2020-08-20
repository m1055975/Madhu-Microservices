package com.mindtree.orderservice.proxy;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.mindtree.orderservice.dto.UserDto;

@Component
public class UserFallBack implements UserServiceFeignProxy {

	@Override
	public ResponseEntity<UserDto> getUserDetails(String userId) {
		UserDto userDto = new UserDto();
		userDto.setUserId("fallback");
		return new ResponseEntity<>(userDto , HttpStatus.NOT_FOUND);
	}

}
