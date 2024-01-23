package com.example.event.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Organizer {
    @SequenceGenerator(
            name = "organizer_seq",
            sequenceName = "organizer_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            generator = "organizer_seq"
    )

    @Id
    private Long id;

    private String name;
    private String email;
    private String phone;
}
