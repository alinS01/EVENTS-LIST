package com.example.event.error;

public class OrganizerDoesNotExistException extends Exception{
    public OrganizerDoesNotExistException(String message) {
        super(message);
    }
}
