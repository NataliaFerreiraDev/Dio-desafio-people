package br.com.dio.diodesafioperson.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.text.ParseException;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PersonNotFoundException extends Exception {

    public PersonNotFoundException(Long id){
        super(String.format("Person with ID %s not found!", id));
    }

}
