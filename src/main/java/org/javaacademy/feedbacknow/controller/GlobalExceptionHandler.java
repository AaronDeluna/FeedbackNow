package org.javaacademy.feedbacknow.controller;

import lombok.extern.slf4j.Slf4j;
import org.javaacademy.feedbacknow.dto.ErrorResponse;
import org.javaacademy.feedbacknow.exeption.NameIsExistException;
import org.javaacademy.feedbacknow.exeption.NameNotFoundException;
import org.javaacademy.feedbacknow.exeption.UuidIsExistException;
import org.javaacademy.feedbacknow.exeption.UuidNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler({
            UuidIsExistException.class,
            NameIsExistException.class
    })
    public ResponseEntity<ErrorResponse> handleExistException(RuntimeException e) {
        log.warn(e.getMessage(), e);
        return buildErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
    }

    @ExceptionHandler({
            UuidNotFoundException.class,
            NameNotFoundException.class
    })
    public ResponseEntity<ErrorResponse> handleNotFountException(RuntimeException e) {
        log.warn(e.getMessage(), e);
        return buildErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
    }

    private ResponseEntity<ErrorResponse> buildErrorResponse(HttpStatus status, String message) {
        return ResponseEntity
                .status(status)
                .body(new ErrorResponse(status.value(), status.name(), message));
    }
}
