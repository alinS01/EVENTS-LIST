package com.example.event.error;

import com.example.event.entity.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@ResponseStatus
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({EventDoesNotExistException.class})
    public ResponseEntity<ErrorMessage> eventDoesNotExist(EventDoesNotExistException e) {

        ErrorMessage errorMessage = new ErrorMessage();

        errorMessage.setStatus(HttpStatus.BAD_REQUEST);
        errorMessage.setMessage(e.getMessage());

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
    //Event location exception
    @ExceptionHandler({EventLocationException.class})
    public ResponseEntity<ErrorMessage> eventLocationException(EventLocationException e) {

        ErrorMessage errorMessage = new ErrorMessage();

        errorMessage.setStatus(HttpStatus.BAD_REQUEST);
        errorMessage.setMessage(e.getMessage());

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
    //Event organizer exception
    @ExceptionHandler({EventOrganizerException.class})
    public ResponseEntity<ErrorMessage> eventOrganizerException(EventOrganizerException e) {

        ErrorMessage errorMessage = new ErrorMessage();

        errorMessage.setStatus(HttpStatus.BAD_REQUEST);
        errorMessage.setMessage(e.getMessage());

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
    // Location does not exist exception
    @ExceptionHandler({LocationDoesNotExistException.class})
    public ResponseEntity<ErrorMessage> locationDoesNotExist(LocationDoesNotExistException e) {

        ErrorMessage errorMessage = new ErrorMessage();

        errorMessage.setStatus(HttpStatus.BAD_REQUEST);
        errorMessage.setMessage(e.getMessage());

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
    // Organizer does not exist exception
    @ExceptionHandler({OrganizerDoesNotExistException.class})
    public ResponseEntity<ErrorMessage> organizerDoesNotExist(OrganizerDoesNotExistException e) {

        ErrorMessage errorMessage = new ErrorMessage();

        errorMessage.setStatus(HttpStatus.BAD_REQUEST);
        errorMessage.setMessage(e.getMessage());

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
    // Participant does not exist exception
    @ExceptionHandler({ParticipantDoesNotExistException.class})
    public ResponseEntity<ErrorMessage> participantDoesNotExist(ParticipantDoesNotExistException e) {

        ErrorMessage errorMessage = new ErrorMessage();

        errorMessage.setStatus(HttpStatus.BAD_REQUEST);
        errorMessage.setMessage(e.getMessage());

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }
    // Review does not exist exception
    @ExceptionHandler({ReviewDoesNotExistException.class})
    public ResponseEntity<ErrorMessage> reviewDoesNotExist(ReviewDoesNotExistException e) {

        ErrorMessage errorMessage = new ErrorMessage();

        errorMessage.setStatus(HttpStatus.BAD_REQUEST);
        errorMessage.setMessage(e.getMessage());

        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }


}
