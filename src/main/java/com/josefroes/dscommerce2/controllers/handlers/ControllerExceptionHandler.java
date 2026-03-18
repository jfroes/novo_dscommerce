package com.josefroes.dscommerce2.controllers.handlers;

import com.josefroes.dscommerce2.dto.CustomError;
import com.josefroes.dscommerce2.services.exceptions.DatabaseException;
import com.josefroes.dscommerce2.services.exceptions.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<CustomError> resourceNotFound(
            ResourceNotFoundException e,
            HttpServletRequest request
    ){
        HttpStatus status = HttpStatus.NOT_FOUND;

        CustomError err = new CustomError(
                Instant.now(),
                status.value(),
                e.getMessage(),
                request.getRequestURI()
        );

        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<CustomError> database(
            DatabaseException e,
            HttpServletRequest request
    ){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        CustomError err = new CustomError(
          Instant.now(),
          status.value(),
          e.getMessage(),
          request.getRequestURI()
        );

        return ResponseEntity.status(status).body(err);
    }
}
