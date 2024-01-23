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

public class Location {

        @SequenceGenerator(
                name = "location_seq",
                sequenceName = "location_seq",
                allocationSize = 1
        )

        @GeneratedValue(
                generator = "location_seq"
        )

        @Id
        private Long id;

        private String name;
        private String address;
        private String city;
        private String country;
        private String postalCode;
}
