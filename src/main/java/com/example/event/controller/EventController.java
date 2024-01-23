package com.example.event.controller;

import com.example.event.entity.Event;
import com.example.event.entity.Participant;
import com.example.event.entity.Review;
import com.example.event.error.*;
import com.example.event.service.EventService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@RestController
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping("/addEvent")
    public void addEvent(@RequestParam String name, @RequestParam String description,
                         @RequestParam String locationId, @RequestParam String organizerId,
                         HttpServletResponse response) throws EventLocationException,
            EventOrganizerException, LocationDoesNotExistException, IOException, OrganizerDoesNotExistException {

        Event event = Event.builder().
                eventName(name).
                description(description).
                reviews(null).
                participants(null).
                organizer(null).
                build();

        try {
            eventService.addEvent(event, Long.valueOf(locationId), Long.valueOf(organizerId));
        } catch (LocationDoesNotExistException |
                 OrganizerDoesNotExistException e) {
           if(e instanceof LocationDoesNotExistException) {
                String redirectPath = "/locationId-error";
                response.sendRedirect(redirectPath);

                return;
           }
           else {
               String redirectPath = "/organizerId-error";
               response.sendRedirect(redirectPath);

               return;
           }
        }

        String redirectPath = "/getAllEvents";
        try {
            response.sendRedirect(redirectPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/addEvent")
    public ModelAndView showAddEventForm() {
        ModelAndView modelAndView = new ModelAndView("add-event");

        return modelAndView;
    }

    @GetMapping("/getAllEvents")
    public ModelAndView getAllEvents() {
        List<Event> eventList = eventService.getAllEvents();

        ModelAndView modelAndView = new ModelAndView("list-events");
        modelAndView.addObject("events", eventList);

        return modelAndView;
    }

    @GetMapping("/getEvent/{id}")
    public ModelAndView getEvent(@PathVariable Long id) throws EventDoesNotExistException {
        ModelAndView modelAndView = new ModelAndView("event-details");
        modelAndView.addObject("event", eventService.getEvent(id));

        return modelAndView;
    }

    @DeleteMapping("/deleteEvent/{id}")
    public void deleteEvent(@PathVariable Long id) throws EventDoesNotExistException {
        eventService.deleteEvent(id);
    }

    @GetMapping("/deleteEvent/{id}")
    public void deleteEvent(@PathVariable Long id, HttpServletResponse response)
            throws EventDoesNotExistException {
        eventService.deleteEvent(id);

        String rutaDorita = "/getAllEvents";

        try {
            response.sendRedirect(rutaDorita);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @PutMapping("/updateEvent/{id}")
    public void updateEvent(@PathVariable Long id, @RequestBody Long locationId, @RequestBody Long organizerId)
            throws EventLocationException, EventOrganizerException, EventDoesNotExistException,
            LocationDoesNotExistException, OrganizerDoesNotExistException {
        Event event = eventService.getEvent(id);
        eventService.updateEventLocation(event, locationId);
        eventService.updateEventOrganizer(event, organizerId);
    }

    /// Participant

    @GetMapping("/addParticipant/{idEvent}")
    public ModelAndView showAddParticipantForm(@PathVariable Long idEvent) {
        ModelAndView modelAndView = new ModelAndView("add-participant");
        modelAndView.addObject("idEvent", idEvent);
        return modelAndView;
    }

    @PostMapping("/addParticipant/{idEvent}")
    public void addParticipant(
            @PathVariable Long idEvent,
            @RequestParam String name,
            @RequestParam String email,
            @RequestParam String phone,
            HttpServletResponse response) throws EventDoesNotExistException {

        Participant participant = new Participant();
        participant.setName(name);
        participant.setEmail(email);
        participant.setPhone(phone);

        eventService.addParticipant(idEvent, participant);

        String redirectPath = "/getEvent/"+idEvent;

        try {
            response.sendRedirect(redirectPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @DeleteMapping("/deleteParticipant/{idEvent}/{idParticipant}")
    public void deleteParticipant(@PathVariable Long idEvent, @PathVariable Long idParticipant)
            throws EventDoesNotExistException, ParticipantDoesNotExistException {
        eventService.deleteEventParticipant(idEvent, idParticipant);
    }

    @PutMapping("/updateParticipant/{idEvent}/{idParticipant}")
    public void updateParticipant(@PathVariable Long idEvent, @PathVariable Long idParticipant,
                                  @RequestBody Participant participant)
            throws EventDoesNotExistException, ParticipantDoesNotExistException {
        eventService.updateEventParticipant(idEvent, idParticipant, participant);
    }

    @GetMapping("/getParticipant/{idEvent}/{idParticipant}")
    public ModelAndView getParticipant(@PathVariable Long idEvent, @PathVariable Long idParticipant)
            throws EventDoesNotExistException, ParticipantDoesNotExistException {
        ModelAndView modelAndView = new ModelAndView("participant-details");
        modelAndView.addObject("participant", eventService.getEvent(idEvent).getParticipant(idParticipant));

        return modelAndView;
    }

    @GetMapping("/getAllParticipants/{idEvent}")
    public List<Participant> getAllParticipants(@PathVariable Long idEvent)
            throws EventDoesNotExistException {
        return eventService.getAllParticipants(idEvent);
    }

    /// Review

    @PostMapping("/addReview/{idEvent}/{idParticipant}")
    public void addReview(@PathVariable Long idEvent, @PathVariable Long idParticipant,
                          @RequestBody Review review) throws EventDoesNotExistException {
        eventService.addReview(idEvent, idParticipant, review);
    }

    @DeleteMapping("/deleteReview/{idEvent}/{idReview}")
    public void deleteReview(@PathVariable Long idEvent, @PathVariable Long idReview)
            throws EventDoesNotExistException, ReviewDoesNotExistException {
        eventService.deleteEventReview(idEvent, idReview);
    }

    @PutMapping("/updateReview/{idEvent}/{idReview}")
    public void updateReview(@PathVariable Long idEvent, @PathVariable Long idReview,
                             @RequestBody Review review)
            throws EventDoesNotExistException, ReviewDoesNotExistException {
        eventService.updateEventReview(idEvent, idReview, review);
    }

    @GetMapping("/getReview/{idEvent}/{idReview}")
    public Review getReview(@PathVariable Long idEvent, @PathVariable Long idReview)
            throws EventDoesNotExistException, ReviewDoesNotExistException {
        return eventService.getEvent(idEvent).getReview(idReview);
    }
}
