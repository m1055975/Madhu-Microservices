package com.mindtree.userservice.service.impl;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import com.mindtree.userservice.dao.UserDao;
import com.mindtree.userservice.dto.ReviewDto;
import com.mindtree.userservice.dto.UserDetailsDto;
import com.mindtree.userservice.dto.UserDto;
import com.mindtree.userservice.exception.DaoException;
import com.mindtree.userservice.exception.ServiceException;
import com.mindtree.userservice.exception.ValidationException;
import com.mindtree.userservice.service.UserService;

@Service
public class UserServiceImplementation implements UserService {
	
	 private final Logger logger = LogManager.getLogger(UserServiceImplementation.class);
	
	@Autowired
	private UserDao userDao;

	@Override
	public UserDto addNewUser(UserDetailsDto userDetailsDto, OAuth2User oauth2User) throws ServiceException {
		if(userDetailsDto.getUserId().length() == 10){
			try {
			return userDao.addNewUser(userDetailsDto , oauth2User);
			}
			catch(DaoException e){
				logger.error(ExceptionUtils.getFullStackTrace(e));
				throw new ServiceException(e.getMessage());
		}
		}
		else {
			throw new ValidationException("User Id is Invalid (length = 10)");
		}
		
	}

	@Override
	public UserDto getUserDetails(String userId) throws ServiceException {
		if(userId.length() == 10){
			try {
			return userDao.getUserDetails(userId);
			}
			catch(DaoException e){
				logger.error(ExceptionUtils.getFullStackTrace(e));
				throw new ServiceException(e.getMessage());
		}
		}
		else{
			throw new ValidationException("User Id is Invalid (length = 10)");
		}
	}

	@Override
	public ReviewDto addReviewToRestaurant( String restaurantId , ReviewDto reviewDto) throws ServiceException {
		if(reviewDto.getUserId().length() != 10) {
			throw new ValidationException("User Id Invalid (length =10)");
		}
	   if(restaurantId.length() != 10) {
			throw new ValidationException("Restaurant Id Invalid (length = 10)");
		}
	   if(reviewDto.getRating() > 5.0) {
		   throw new ValidationException("Rating can't be more than 5.0");
	   }
		return userDao.addReviewToRestaurant( restaurantId ,reviewDto);
		}
			
	}


