package com.revature.notecard.service.Exceptions;

public class MissingAuthTokenException extends AuthenticationException {

    public MissingAuthTokenException() {
        super("There was no authorization token found on the request");
    }

}