package org.example.fullstack.service;

import org.example.fullstack.dto.PersonRequest;
import org.example.fullstack.dto.PersonResponse;
import org.example.fullstack.entity.Person;
import org.example.fullstack.exceptions.ResourceNotFoundException;
import org.example.fullstack.exceptions.DuplicateResourceException;
import org.example.fullstack.repository.PersonRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PersonService {

    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    // CREATE
    @Transactional
    public PersonResponse createPerson(PersonRequest request) {

        if (personRepository.existsByEmail(request.email())) {
            throw new DuplicateResourceException(
                    "A student with this email already exists"
            );
        }

        Person person = new Person();
        person.setName(request.name());

        person.setEmail(request.email());

        Person savedPerson = personRepository.save(person);

        return mapToResponse(savedPerson);
    }

    // READ ALL
    @Transactional(readOnly = true)
    public List<PersonResponse> getAll() {

        return personRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // READ ONE
    @Transactional(readOnly = true)
    public PersonResponse getPersonById(Long id) {

        Person person = findPersonOrThrow(id);

        return mapToResponse(person);
    }

    private Person findPersonOrThrow(Long id) {

        return personRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Student not found with ID: " + id
                ));
    }

    private PersonResponse mapToResponse(Person person) {

        return new PersonResponse(
                person.getId(),
                person.getName(),
                person.getEmail()
        );
    }


}
