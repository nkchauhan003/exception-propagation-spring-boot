package com.codeburps.exception.handler;

import com.codeburps.exception.ProductNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> productNotFoundException(
            ProductNotFoundException ex) {
        List<String> errors = List.of(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.NOT_FOUND,
                ex.getMessage(),
                errors);
        log.error(ExceptionUtils.getStackTrace(ex));
        return ResponseEntityBuilder.build(errorResponse);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> exception(
            Exception ex) {
        List<String> errors = List.of(ex.getMessage());
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                ex.getMessage(),
                errors);
        return ResponseEntityBuilder.build(errorResponse);
    }

    private static class ResponseEntityBuilder {
        public static ResponseEntity<Object> build(ErrorResponse errorResponse) {
            return new ResponseEntity<>(errorResponse, errorResponse.getStatus());
        }
    }
}
