package com.mindtree.orderservice.exception;

public class OrderManagementApplicationException extends Exception {

	
	private static final long serialVersionUID = 1L;

	public OrderManagementApplicationException() {
		super();
		
	}

	public OrderManagementApplicationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	
	}

	public OrderManagementApplicationException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public OrderManagementApplicationException(String message) {
		super(message);
		
	}

	public OrderManagementApplicationException(Throwable cause) {
		super(cause);
	
	}

}
