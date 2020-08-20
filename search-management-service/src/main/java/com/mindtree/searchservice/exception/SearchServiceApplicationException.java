package com.mindtree.searchservice.exception;

public class SearchServiceApplicationException extends Exception {

	
	private static final long serialVersionUID = 1L;

	public SearchServiceApplicationException() {
		super();
	}

	public SearchServiceApplicationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public SearchServiceApplicationException(String message, Throwable cause) {
		super(message, cause);
	}

	public SearchServiceApplicationException(String message) {
		super(message);

	}

	public SearchServiceApplicationException(Throwable cause) {
		super(cause);
	}
	
	

}
