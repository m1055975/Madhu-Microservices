package com.mindtree.userservice.exception;

public class UserServiceApplicationException extends Exception {

	
	private static final long serialVersionUID = 1L;

	public UserServiceApplicationException() {
		super();
		
	}

	public UserServiceApplicationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public UserServiceApplicationException(String message, Throwable cause) {
		super(message, cause);
	
	}

	public UserServiceApplicationException(String message) {
		super(message);
	
	}

	public UserServiceApplicationException(Throwable cause) {
		super(cause);
		
	}

}
