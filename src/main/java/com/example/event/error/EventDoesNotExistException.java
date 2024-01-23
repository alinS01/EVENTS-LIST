package com.example.event.error;

public class EventDoesNotExistException extends Exception{
    public EventDoesNotExistException(String message) {
        super(message);
    }
}
