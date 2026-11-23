package org.example.fullstack.controllers;

import org.example.fullstack.dto.PersonRequest;
import org.example.fullstack.dto.PersonResponse;
import org.example.fullstack.service.PersonService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<PersonResponse> createPerson(
            @Valid @RequestBody PersonRequest request) {

        PersonResponse response =
                personService.createPerson(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<PersonResponse>> getAll() {

        return ResponseEntity.ok(
                personService.getAll()
        );
    }

    // READ ONE
    @GetMapping("/{id}")
    public ResponseEntity<PersonResponse> getPersonById(
            @PathVariable Long id) {

        return ResponseEntity.ok(
                personService.getPersonById(id)
        );
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<PersonResponse> updatePerson(
            @PathVariable Long id,
            @Valid @RequestBody PersonRequest request) {

        return ResponseEntity.ok(
                personService.updatePerson(id, request)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(
            @PathVariable Long id) {

        personService.deletePerson(id);

        return ResponseEntity.noContent().build();
    }
}
