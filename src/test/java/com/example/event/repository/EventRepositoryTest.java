package com.example.event.repository;

import com.example.event.entity.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class EventRepositoryTest {

    @Autowired
    private EventRepository eventRepositoryUnderTest;

    @Test
    public void addEvent(){
        Location location = Location.builder()
                .name("Test ssssssLocation")
                .address("Testssssssss Address")
                .city("Test Csssssssity")
                .country("Test Cssssountry")
                .postalCode("11")
                .build();

        Organizer organizer = Organizer.builder()
                .name("Test Ossssrganizer")
                .email("test@gmaissl.com")
                .phone("0000000000")
                .build();
        Participant participant = Participant.builder()
                .name("Tesddt Participant")
                .phone("112321")
                .email("ssss@gmaol.com")
                ///fix cu ideea lui dailyCodeBuffer
                .events(null)
                .build();

        Review review = Review.builder()
                .rating(5)
                .comment("dadad")
                .author(participant)
                .event(null)
                .build();

        Event event = Event.builder()
                .eventName("da")
                .description("da")
                .location(location)
                .participants(List.of())
                .organizer(organizer)
                .reviews(List.of(review))
                .build();

        review.setEvent(event);
        participant.setEvents(List.of(event));

        eventRepositoryUnderTest.save(event);
    }
}