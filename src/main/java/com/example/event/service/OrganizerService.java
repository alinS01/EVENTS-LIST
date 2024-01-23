package com.example.event.service;

import com.example.event.entity.Organizer;
import com.example.event.error.EventOrganizerException;
import com.example.event.error.OrganizerDoesNotExistException;

import java.util.List;

public interface OrganizerService {
    Organizer addOrganizer(Organizer organizer);
    void deleteOrganizer(Long id) throws OrganizerDoesNotExistException;
    Organizer updateOrganizer(Organizer organizer, Long id) throws OrganizerDoesNotExistException;
    Organizer getOrganizer(Long id) throws EventOrganizerException, OrganizerDoesNotExistException;
    List<Organizer> getAllOrganizers();
}
