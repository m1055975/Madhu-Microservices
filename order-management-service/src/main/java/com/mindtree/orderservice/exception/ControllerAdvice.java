package com.mindtree.orderservice.exception;
import org.springframework.http.HttpStatus;
import javax.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@RestControllerAdvice
public class ControllerAdvice {

	@ExceptionHandler(OrderManagementApplicationException.class)
	public ExceptionResponse handleAppException(final OrderManagementApplicationException exception, final HttpServletRequest request)
	{
		ExceptionResponse error = new ExceptionResponse();
		error.setErrorMessage(exception.getMessage());
		error.setRequestURL(request.getRequestURI());
		
		return error;
	}
}