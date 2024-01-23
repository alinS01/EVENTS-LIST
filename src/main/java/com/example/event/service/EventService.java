package com.example.event.service;

import com.example.event.entity.Event;
import com.example.event.entity.Participant;
import com.example.event.entity.Review;
import com.example.event.error.*;

import java.util.List;

public interface EventService {
    Event addEvent(Event event, Long locationId, Long organizerId) throws EventLocationException, EventOrganizerException, LocationDoesNotExistException, OrganizerDoesNotExistException;
    void deleteEvent(Long id) throws EventDoesNotExistException;
    Event updateEventLocation(Event event, Long locationId) throws EventLocationException, LocationDoesNotExistException;
    Event updateEventOrganizer(Event event, Long organizerId) throws EventOrganizerException, OrganizerDoesNotExistException;
    Event getEvent(Long id) throws EventDoesNotExistException;
    List<Event> getAllEvents();
    void addParticipant(Long idEvent, Participant participant) throws EventDoesNotExistException;
    void deleteEventParticipant(Long idEvent, Long idParticipant) throws EventDoesNotExistException, ParticipantDoesNotExistException;
    void updateEventParticipant(Long idEvent, Long id, Participant participant) throws ParticipantDoesNotExistException, EventDoesNotExistException;
    void addReview(Long idEvent, Long idParticipant, Review review) throws EventDoesNotExistException;
    void deleteEventReview(Long idEvent, Long idReview) throws EventDoesNotExistException, ReviewDoesNotExistException;
    void updateEventReview(Long idEvent, Long idReview, Review review) throws ReviewDoesNotExistException, EventDoesNotExistException;
    List<Review> getAllReviews(Long idEvent);

    List<Participant> getAllParticipants(Long idEvent) throws EventDoesNotExistException;
}
