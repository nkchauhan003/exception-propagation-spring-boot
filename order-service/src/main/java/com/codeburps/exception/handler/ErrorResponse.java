package com.codeburps.exception.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor @Getter @Setter
@NoArgsConstructor
public class ErrorResponse {
    private HttpStatus status;
    private String message;
    private List errors;
}
