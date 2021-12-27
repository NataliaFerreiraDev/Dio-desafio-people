package br.com.dio.diodesafioperson.controllers;

import br.com.dio.diodesafioperson.dto.PersonDTO;
import br.com.dio.diodesafioperson.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<PersonDTO>> findAllPerson() throws ParseException {

        List<PersonDTO> personDTOList = personService.findAllPersonDTO();

        return ResponseEntity.status(HttpStatus.OK).body(personDTOList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PersonDTO> findPersonById(@PathVariable @Valid Long id) throws ParseException {

        return ResponseEntity.status(HttpStatus.OK).body(personService.findById(id));

    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonDTO> updatePerson(@RequestBody PersonDTO personDTO, @PathVariable Long id)
            throws ParseException {

        PersonDTO personUpdate = personService.updatePerson(personDTO, id);

        return ResponseEntity.status(HttpStatus.OK).body(personUpdate);
    }

}
