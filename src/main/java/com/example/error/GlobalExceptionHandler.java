package com.example.error;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler(MethodArgumentNotValidException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorHandler handleResourceNoFoundException(MethodArgumentNotValidException ex, HttpServletRequest request) {
		ErrorHandler response = new ErrorHandler();
		response.setTimestamp(LocalDateTime.now());
		response.setStatus(HttpStatus.NOT_FOUND.value());
		response.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
		Map<String, String> errors = new HashMap<>();
		for (FieldError error : ex.getBindingResult().getFieldErrors()) {
			errors.put(error.getField(), error.getDefaultMessage());
		}
		response.setMessage(errors);
		response.setPath(request.getRequestURI());
		return response;
	}
	
	@ExceptionHandler(NullPointerException.class)
	@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
	@ResponseBody
	public ErrorHandlerNullPointer handleNullPointerException(NullPointerException ex, HttpServletRequest request) {
		ErrorHandlerNullPointer response = new ErrorHandlerNullPointer();
		response.setTimestamp(LocalDateTime.now());
		response.setStatus(HttpStatus.NOT_ACCEPTABLE.value());
		response.setError(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase());
		response.setMessage(ex.getMessage());
		response.setPath(request.getRequestURI());
		return response;
	}
	
	@ExceptionHandler(CustomerNotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ResponseBody
	public ErrorHandlerNullPointer handleCustomerNotFoundException(CustomerNotFoundException ex, HttpServletRequest request) {
		ErrorHandlerNullPointer response = new ErrorHandlerNullPointer();
		response.setTimestamp(LocalDateTime.now());
		response.setStatus(HttpStatus.NOT_FOUND.value());
		response.setError(HttpStatus.NOT_FOUND.getReasonPhrase());
		response.setMessage(ex.getMessage());
		response.setPath(request.getRequestURI());
		return response;
	}
}