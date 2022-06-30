package com.revature.notecard.service.exceptions;

public class MissingAuthTokenException extends AuthenticationException {

    public MissingAuthTokenException() {
        super("There was no authorization token found on the request");
    }

}
