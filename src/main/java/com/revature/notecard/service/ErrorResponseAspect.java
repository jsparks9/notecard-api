package com.revature.notecard.service;

import com.revature.notecard.service.exceptions.AuthenticationException;
import com.revature.notecard.service.exceptions.IncorrectPasswordException;
import com.revature.notecard.service.exceptions.ResourcePersistenceException;
import com.revature.notecard.service.exceptions.TokenParseException;
import com.revature.notecard.service.dtos.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice // Setting class as an advice controller to inject custom exception handlers
public class ErrorResponseAspect {


    // Unauthorized token status exception handler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler
    public ErrorResponse handleTokenParseException(TokenParseException e) {
        return new ErrorResponse(401, e.getMessage());
    }

    // Incorrect login password status exception handler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler
    public ErrorResponse handleResourceNotFoundException(IncorrectPasswordException e) {
        return new ErrorResponse(401, e.getMessage());
    }

    // Unauthorized registration authentication status exception handler
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler
    public ErrorResponse handleResourceNotFoundException(AuthenticationException e) {
        return new ErrorResponse(401, e.getMessage());
    }

    // Resource not found status exception handler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public ErrorResponse handleResourceNotFoundException(EntityNotFoundException e) {
        return new ErrorResponse(404, e.getMessage());
    }

    // Conflict status exception handler
    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler
    public ErrorResponse handleResourcePersistenceException(ResourcePersistenceException e) {
        return new ErrorResponse(409, e.getMessage());
    }

    // Internal server error status exception handler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResponse handleOtherException(Throwable t) {
        t.printStackTrace();
        return new ErrorResponse(500, "An internal server error occurred");
    }
}
