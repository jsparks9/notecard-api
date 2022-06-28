package com.revature.notecard.service.Exceptions;

// exists only for logging purposes
public class IncorrectPasswordException extends AuthenticationException {

    public IncorrectPasswordException() { super(); }

    public IncorrectPasswordException(String message) {
        super(message);
    }

    public IncorrectPasswordException(String message, Throwable cause) {
        super(message, cause);
    }
}
