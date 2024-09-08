package com.example.userserviceecom24.Exceptions;

public class PasswordDoesNotMatchException extends Exception {
    public PasswordDoesNotMatchException(String message) {
        super(message);
    }
}
