package com.backend.restApi.demo.exception;

public class userNotFoundException extends RuntimeException {
    public userNotFoundException(Long id) {
        super("The id is not found." + id);
    }
}
