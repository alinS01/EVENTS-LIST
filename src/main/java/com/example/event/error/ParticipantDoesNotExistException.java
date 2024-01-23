package com.example.event.error;

public class ParticipantDoesNotExistException extends Exception{
    public ParticipantDoesNotExistException(String message) {
        super(message);
    }
}
