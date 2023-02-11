package com.codeburps.exception.handler;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.List;

@AllArgsConstructor @Getter @Setter
public class ErrorResponse {
    private HttpStatus status;
    private String message;
    private List errors;
}
