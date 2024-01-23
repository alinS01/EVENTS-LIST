package com.example.event.service;

import com.example.event.entity.Organizer;
import com.example.event.error.EventOrganizerException;
import com.example.event.error.OrganizerDoesNotExistException;
import com.example.event.repository.OrganizerRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizerServiceImpl implements OrganizerService{
    private final OrganizerRepository organizerRepository;

    @Autowired
    public OrganizerServiceImpl(OrganizerRepository organizerRepository) {
        this.organizerRepository = organizerRepository;
    }

    @Override
    public Organizer addOrganizer(Organizer organizer) {
        return organizerRepository.save(organizer);
    }

    @Override
    public void deleteOrganizer(Long id) throws OrganizerDoesNotExistException {
        if(organizerRepository.findById(id).isEmpty())
        {
            throw new OrganizerDoesNotExistException("Organizer does not exist");
        }
        organizerRepository.deleteById(id);
    }

    @Override
    public Organizer updateOrganizer(Organizer organizer, Long id)
            throws OrganizerDoesNotExistException {

        if(organizerRepository.findById(id).isEmpty())
        {
            throw new OrganizerDoesNotExistException("Organizer does not exist");
        }

        Organizer previousOrganizer = organizerRepository.findById(id).get();

        previousOrganizer.setName(organizer.getName());
        previousOrganizer.setEmail(organizer.getEmail());
        previousOrganizer.setPhone(organizer.getPhone());


        return organizerRepository.save(previousOrganizer);
    }

    @Override
    public Organizer getOrganizer(Long id) throws OrganizerDoesNotExistException {

        if(organizerRepository.findById(id).isEmpty())
        {
            throw new OrganizerDoesNotExistException("Organizer does not exist");
        }

        return organizerRepository.findById(id).get();
    }

    @Override
    public List<Organizer> getAllOrganizers() {
        return organizerRepository.findAll();
    }
}
