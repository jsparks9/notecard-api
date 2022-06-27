package com.revature.notecard.service.exceptions;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super("No resource found using the provided search parameters.");
    }
}

