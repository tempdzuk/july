package com.test.julyOld.service.exception;

public class InvalidArgumentException extends RuntimeException {
    public InvalidArgumentException() {
        super("Required value/values is/are empty");
    }
}
