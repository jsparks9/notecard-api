package com.revature.notecard.common.utils.exceptions;

import org.springframework.web.bind.annotation.ExceptionHandler;

public class ResourcePersistenceException extends RuntimeException {

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
