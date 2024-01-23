package com.example.event.service;

import com.example.event.entity.Location;
import com.example.event.error.EventLocationException;
import com.example.event.error.LocationDoesNotExistException;
import com.example.event.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LocationServiceImpl implements LocationService{

    private final LocationRepository locationRepository;

    @Autowired
    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public Location addLocation(Location location) {
        return locationRepository.save(location);
    }

    @Override
    public void deleteLocation(Long id) throws LocationDoesNotExistException {

        if(locationRepository.findById(id).isEmpty()){
            throw new LocationDoesNotExistException("Location does not exist");
        }

        locationRepository.deleteById(id);
    }

    @Override
    public Location updateLocation(Location location, Long id) throws LocationDoesNotExistException {

        if(locationRepository.findById(id).isEmpty()){
            throw new LocationDoesNotExistException("Location does not exist");
        }

        Location previousLocation = locationRepository.findById(id).get();
        previousLocation.setName(location.getName());
        previousLocation.setAddress(location.getAddress());
        previousLocation.setCity(location.getCity());
        previousLocation.setCountry(location.getCountry());
        previousLocation.setPostalCode(location.getPostalCode());

        return locationRepository.save(previousLocation);
    }

    @Override
    public Location getLocation(Long id) throws LocationDoesNotExistException {

        if(locationRepository.findById(id).isEmpty()){
            throw new LocationDoesNotExistException("Location does not exist");
        }

        return locationRepository.findById(id).get();
    }

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public Boolean checkLocationExists(String locationName) {
        return locationRepository.existsByName(locationName);
    }
}
