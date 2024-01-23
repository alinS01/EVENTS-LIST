package com.example.event.repository;

import com.example.event.entity.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {

    /// posibil sa nu merga
    Boolean existsByName(String locationName);
}
