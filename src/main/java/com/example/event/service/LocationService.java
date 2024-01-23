package com.example.event.service;

import com.example.event.entity.Location;
import com.example.event.error.EventLocationException;
import com.example.event.error.LocationDoesNotExistException;

import java.util.List;

public interface LocationService {
    Location addLocation(Location location);
    void deleteLocation(Long id) throws LocationDoesNotExistException;
    Location updateLocation(Location location, Long id) throws LocationDoesNotExistException;
    Location getLocation(Long id) throws LocationDoesNotExistException;
    List<Location> getAllLocations();

    Boolean checkLocationExists(String locationName);
}
