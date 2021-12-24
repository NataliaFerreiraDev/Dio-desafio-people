package br.com.dio.diodesafioperson.services;

import br.com.dio.diodesafioperson.dto.PersonDTO;
import br.com.dio.diodesafioperson.entities.Person;
import br.com.dio.diodesafioperson.entities.Phone;
import br.com.dio.diodesafioperson.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PhoneService phoneService;

    public String createPerson(PersonDTO personDTO) throws ParseException {
        Person personEntity = new Person();

        String cpf = personEntity.getCpf();

        List<Person> personFound = personRepository.findByCpf(cpf);

        if (personFound.isEmpty()){
            personEntity = conversaoPersonEntity( personDTO, personEntity);
            personRepository.save(personEntity);

            return "Person create with success";
        }

        return "Person already exists with this cpf";
    }

    //Conversão de DTO para Entity
    private Person conversaoPersonEntity(PersonDTO personDTO, Person personEntity) throws ParseException {

        List<Phone> phonesEntities = new ArrayList<>();
        phonesEntities = phoneService.conversaoPhoneEntities(personDTO.getPhones(), phonesEntities) ;


        personEntity.setFirstName(personDTO.getFirstName());
        personEntity.setLastName(personDTO.getLastName());
        personEntity.setCpf(personDTO.getCpf());
        personEntity.setBirthDate(personDTO.getBirthDate());

        return personEntity;

    }


}
