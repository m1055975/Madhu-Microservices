package com.mindtree.userservice.exception;

public class HystrixFallBackException extends DaoException {

	
	private static final long serialVersionUID = 1L;

	public HystrixFallBackException() {
		super();
		
	}

	public HystrixFallBackException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

	public HystrixFallBackException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public HystrixFallBackException(String message) {
		super(message);
		
	}

	public HystrixFallBackException(Throwable cause) {
		super(cause);
		
	}
	

}
