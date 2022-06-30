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

@RestControllerAdvice
public class ErrorResponseAspect {



    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler
    public ErrorResponse handleTokenParseException(TokenParseException e) {
        return new ErrorResponse(401, e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler
    public ErrorResponse handleResourceNotFoundException(IncorrectPasswordException e) {
        return new ErrorResponse(401, e.getMessage());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler
    public ErrorResponse handleResourceNotFoundException(AuthenticationException e) {
        return new ErrorResponse(401, e.getMessage());
    }


    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public ErrorResponse handleResourceNotFoundException(EntityNotFoundException e) {
        return new ErrorResponse(404, e.getMessage());
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    @ExceptionHandler
    public ErrorResponse handleResourcePersistenceException(ResourcePersistenceException e) {
        return new ErrorResponse(409, e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResponse handleOtherException(Throwable t) {
        t.printStackTrace();
        return new ErrorResponse(500, "An internal server error occurred");
    }
}
