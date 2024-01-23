package com.example.event.controller;

import com.example.event.entity.Organizer;
import com.example.event.error.EventOrganizerException;
import com.example.event.error.OrganizerDoesNotExistException;
import com.example.event.service.OrganizerService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.List;

@RestController
public class OrganizerController {
    private final OrganizerService organizerService;

    @Autowired
    public OrganizerController(OrganizerService organizerService) {
        this.organizerService = organizerService;
    }

    @PostMapping("/addOrganizer")
    public void addOrganizer(@RequestParam String name,
                             @RequestParam String email,
                             @RequestParam String phone,
                             HttpServletResponse response) {

        Organizer organizer = Organizer.builder().
                name(name).
                email(email).
                phone(phone).
                build();

        organizerService.addOrganizer(organizer);

        String redirectPath = "/getAllOrganizers";

        try {
            response.sendRedirect(redirectPath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @GetMapping("/addOrganizer")
    public ModelAndView showAddOrganizer() {
        ModelAndView modelAndView = new ModelAndView("add-organizer");

        return modelAndView;
    }


    @DeleteMapping("/deleteOrganizer/{id}")
    public void deleteOrganizer(@PathVariable Long id) throws OrganizerDoesNotExistException {
        organizerService.deleteOrganizer(id);
    }

    @GetMapping("/deleteOrganizer/{id}")
    public void deleteOrganizer(@PathVariable Long id, HttpServletResponse response)
            throws OrganizerDoesNotExistException, IOException {
        try {
            organizerService.deleteOrganizer(id);
        } catch (DataIntegrityViolationException e) {
            String errorPath = "/errorDeleteOrganizer";
            response.sendRedirect(errorPath);

            return;
        }

        String redirectPath = "/getAllOrganizers";

        try{
            response.sendRedirect(redirectPath);
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    @GetMapping("/errorDeleteOrganizer")
    public ModelAndView errorDeleteOrganizer(){
        ModelAndView modelAndView = new ModelAndView("organizer-delete-error");
        return modelAndView;
    }
    @PutMapping("/updateOrganizer/{id}")
    public void updateOrganizer(@RequestBody Organizer organizer, @PathVariable Long id)
            throws EventOrganizerException, OrganizerDoesNotExistException {
        if(organizerService.getOrganizer(id) == null) {
            System.out.println("Organizer with id " + id + " not found.");
            return;
        }
        organizerService.updateOrganizer(organizer, id);
    }

    @GetMapping("/getOrganizer/{id}")
    public ModelAndView getOrganizer(@PathVariable Long id)
            throws EventOrganizerException, OrganizerDoesNotExistException {
        ModelAndView modelAndView = new ModelAndView("organizer-details");
        modelAndView.addObject("organizer", organizerService.getOrganizer(id));

        return modelAndView;
    }

    @GetMapping("/getAllOrganizers")
    public ModelAndView getAllOrganizers() {
        ModelAndView modelAndView = new ModelAndView("list-organizers");
        modelAndView.addObject("organizers", organizerService.getAllOrganizers());

        return modelAndView;
    }

    @GetMapping("/organizerId-error")
    public ModelAndView organizerIdError() {
        ModelAndView modelAndView = new ModelAndView("organizerId-error");

        return modelAndView;
    }
}
