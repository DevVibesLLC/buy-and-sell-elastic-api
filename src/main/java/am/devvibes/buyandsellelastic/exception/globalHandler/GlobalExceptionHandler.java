package am.devvibes.buyandsellelastic.exception.globalHandler;


import am.devvibes.buyandsellelastic.exception.NotFoundException;
import am.devvibes.buyandsellelastic.exception.SomethingWentWrongException;
import am.devvibes.buyandsellelastic.util.ApiError;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(code = HttpStatus.NOT_FOUND)
	public ResponseEntity<ApiError> notFoundException(HttpServletRequest req, NotFoundException e) {
		logError(req, e);
		return buildResponse(HttpStatus.NOT_FOUND, e.getMessage(), req.getRequestURI());
	}

	@ExceptionHandler(SomethingWentWrongException.class)
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ResponseEntity<ApiError> somethingWentWrongException(HttpServletRequest req, SomethingWentWrongException e) {
		logError(req, e);
		return buildResponse(HttpStatus.BAD_REQUEST, e.getMessage(), req.getRequestURI());
	}


	private ResponseEntity<ApiError> buildResponse(HttpStatus httpStatus, String message, String uri) {
		var errors = new HashMap<String, String>();
		errors.put("message", message);
		var apiError = new ApiError(httpStatus.value(), uri, errors);
		return ResponseEntity.status(httpStatus).body(apiError);
	}

	private void logError(HttpServletRequest req, Exception e) {
		log.error(e.getMessage());
		log.error("RequestURI {}", req.getRequestURI());
		e.printStackTrace();
	}

}