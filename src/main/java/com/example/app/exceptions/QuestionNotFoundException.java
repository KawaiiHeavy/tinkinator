package com.example.app.exceptions;

public class QuestionNotFoundException extends RuntimeException {
    public QuestionNotFoundException(String message){
        super(message);
    }
}
