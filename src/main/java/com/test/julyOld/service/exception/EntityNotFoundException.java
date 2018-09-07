package com.test.julyOld.service.exception;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException() {
        super("There is no such entity");
    }
}
