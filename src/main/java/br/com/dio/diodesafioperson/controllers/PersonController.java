package br.com.dio.diodesafioperson.controllers;

import br.com.dio.diodesafioperson.dto.PersonDTO;
import br.com.dio.diodesafioperson.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;

@RestController
@RequestMapping("/api/v1/people")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping
    public ResponseEntity<PersonDTO> createPerson(@RequestBody PersonDTO personDTO) throws ParseException {

        String personSaved = personService.createPerson(personDTO);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }



}
