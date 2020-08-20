package com.mindtree.userservice.service;

import org.springframework.security.oauth2.core.user.OAuth2User;

import com.mindtree.userservice.dto.ReviewDto;
import com.mindtree.userservice.dto.UserDetailsDto;
import com.mindtree.userservice.dto.UserDto;
import com.mindtree.userservice.exception.ServiceException;

public interface UserService {

	UserDto addNewUser(UserDetailsDto userDetailsDto, OAuth2User oauth2User) throws ServiceException;

	UserDto getUserDetails(String userId) throws ServiceException;

	ReviewDto addReviewToRestaurant(String userId, ReviewDto reviewDto) throws ServiceException;

}
