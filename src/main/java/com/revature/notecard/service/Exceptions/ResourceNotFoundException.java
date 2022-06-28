package com.revature.notecard.service.Exceptions;

import javax.persistence.EntityNotFoundException;

public class ResourceNotFoundException extends EntityNotFoundException {
    public ResourceNotFoundException() {
        super("No resource found using the provided search params.");
    }
}
