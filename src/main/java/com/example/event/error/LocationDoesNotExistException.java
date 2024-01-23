package com.example.event.error;

public class LocationDoesNotExistException extends Exception{
    public LocationDoesNotExistException(String message) {
        super(message);
    }
}
