package com.example.app.exceptions;

public class SolutionNotFoundException extends RuntimeException {
    public SolutionNotFoundException(String message) {
        super(message);
    }
}
