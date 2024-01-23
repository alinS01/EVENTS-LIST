package com.example.event.controller;

import com.example.event.entity.Location;
import com.example.event.error.EventLocationException;
import com.example.event.error.LocationDoesNotExistException;
import com.example.event.service.LocationService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@RestController
public class LocationController {
    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/existsLocation")
    public void checkLocationExists(@RequestBody String locationName) {
        if (locationService.checkLocationExists(locationName)) {
            System.out.println("Location exists");
        } else {
            System.out.println("Location does not exist");
        }
    }
    @PostMapping("/addLocation")
    public void addLocation(@RequestParam String name,
                            @RequestParam String address,
                            @RequestParam String city,
                            @RequestParam String country,
                            @RequestParam String postalCode,
                            HttpServletResponse response) {

        Location location = Location.builder().
                name(name).
                address(address).
                city(city).
                country(country).
                postalCode(postalCode).
                build();

        locationService.addLocation(location);

        String redirectPath = "/getLocations";

        try {
            response.sendRedirect(redirectPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/addLocation")
    public ModelAndView addLocation() {
        ModelAndView modelAndView = new ModelAndView("add-location");

        return modelAndView;
    }

    @GetMapping("/getLocation/{id}")
    public ModelAndView getLocation(@PathVariable Long id) throws LocationDoesNotExistException {
        ModelAndView modelAndView = new ModelAndView("location-details");
        modelAndView.addObject("location", locationService.getLocation(id));

        return modelAndView;
    }

    @GetMapping("/getLocations")
    public ModelAndView getLocations() {
        ModelAndView modelAndView = new ModelAndView("list-locations");
        modelAndView.addObject("locations", locationService.getAllLocations());

        return modelAndView;
    }

    @DeleteMapping("/deleteLocation/{id}")
    public void deleteLocation(@PathVariable Long id) throws LocationDoesNotExistException {
        locationService.deleteLocation(id);
    }
    @GetMapping("/deleteLocation/{id}")
    public void deleteLocation(@PathVariable Long id, HttpServletResponse response)
            throws LocationDoesNotExistException, IOException {
        try {
            locationService.deleteLocation(id);
        } catch (DataIntegrityViolationException e) {
            String errorPath = "/errorDeleteLocation";
            response.sendRedirect(errorPath);

            return;
        }

        String redirectPath = "/getLocations";

        try {
            response.sendRedirect(redirectPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/errorDeleteLocation")
    public ModelAndView errorDeleteLocation(){
        ModelAndView modelAndView = new ModelAndView("location-delete-error");
        return modelAndView;
    }
    @PutMapping("/updateLocation/{id}")
    public void updateLocation(@RequestBody Location location, @PathVariable Long id)
            throws LocationDoesNotExistException {

        locationService.updateLocation(location,id);
    }

    @GetMapping("locationId-error")
    public ModelAndView locationIdError(){
        ModelAndView modelAndView = new ModelAndView("locationId-error");
        return modelAndView;
    }
}
