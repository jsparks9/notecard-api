package com.revature.notecard.service.aspects;

import com.revature.notecard.service.dtos.ErrorResponse;
import com.revature.notecard.service.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorResponseAspect {

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler
    public ErrorResponse handleResourceNotFoundException(ResourceNotFoundException e) {
        return new ErrorResponse(404, e.getMessage());
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResponse handleGeneralExceptions(Throwable t) {
        return new ErrorResponse(500, t.getMessage());
    }

    // TODO : Any errors to be thrown in other areas, just throw them there and add them here
}
