package com.example.event.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Review {

    @SequenceGenerator(
            name = "review_seq",
            sequenceName = "review_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "review_seq"
    )

    @Id
    private Long id;

    private int rating;
    private String comment;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "participant_id")
    private Participant author;

    @ManyToOne(cascade = CascadeType.ALL, optional = false)
    @JoinColumn(name = "event_id")
    private Event event;
}
