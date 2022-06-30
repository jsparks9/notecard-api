package com.revature.notecard.service.exceptions;

public class ResourcePersistenceException extends RuntimeException{
    public ResourcePersistenceException(String message) {
        super(message);
    }

    public ResourcePersistenceException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourcePersistenceException(Throwable cause) {
        super(cause);
    }
}
