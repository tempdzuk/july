package com.test.julyOld.service.exception;

public class NotAuthorizedUserException extends RuntimeException {
    public NotAuthorizedUserException(String s) {
        super(s);
    }
}
