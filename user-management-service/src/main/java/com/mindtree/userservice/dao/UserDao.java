package com.mindtree.userservice.dao;

import org.springframework.security.oauth2.core.user.OAuth2User;

import com.mindtree.userservice.dto.ReviewDto;
import com.mindtree.userservice.dto.UserDetailsDto;
import com.mindtree.userservice.dto.UserDto;
import com.mindtree.userservice.exception.DaoException;

public interface UserDao {

	UserDto getUserDetails(String userId) throws DaoException;

	ReviewDto addReviewToRestaurant(String userId, ReviewDto reviewDto) throws DaoException;

	UserDto addNewUser(UserDetailsDto userDetailsDto, OAuth2User oauth2User) throws DaoException;


}
