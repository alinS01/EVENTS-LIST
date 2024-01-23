package com.example.event.error;

public class ReviewDoesNotExistException extends Exception{
    public ReviewDoesNotExistException(String message) {
        super(message);
    }
}
