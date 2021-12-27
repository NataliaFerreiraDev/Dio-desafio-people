package br.com.dio.diodesafioperson.services;

import br.com.dio.diodesafioperson.dto.PersonDTO;
import br.com.dio.diodesafioperson.dto.PhoneDTO;
import br.com.dio.diodesafioperson.entities.Person;
import br.com.dio.diodesafioperson.entities.Phone;
import br.com.dio.diodesafioperson.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public List<PersonDTO> findAllPersonDTO() throws ParseException {

        List<Person> personList = personRepository.findAll();

        List<PersonDTO> personDTOList = new ArrayList<>();

        personDTOList = conversaoPersonDTOs(personList, personDTOList);

        return personDTOList;
    }

    public PersonDTO findById(Long id) throws ParseException {

        Person person = getPersonEntity(id);

        PersonDTO personDTO= new PersonDTO();

        personDTO = conversaoPersonDTO(person, personDTO);

        return personDTO;
    }

    public PersonDTO updatePerson(PersonDTO personDTO, Long id) throws ParseException {

       Person personEntity = new Person();

        personEntity =  conversaoPersonEntity(personDTO, personEntity);

        Person pesonEntitySaved = personRepository.save(personEntity);

        PersonDTO personToSave = new PersonDTO();

        personToSave = conversaoPersonDTO(pesonEntitySaved, personToSave);

        return personToSave;
    }


    //Conversão de DTO para Entity
    private Person conversaoPersonEntity(PersonDTO personDTO, Person personEntity) throws ParseException {

        List<Phone> phonesEntities = new ArrayList<>();
        phonesEntities = phoneService.conversaoPhoneEntities(personDTO.getPhones(), phonesEntities) ;

        personEntity.setId(personDTO.getId());
        personEntity.setFirstName(personDTO.getFirstName());
        personEntity.setLastName(personDTO.getLastName());
        personEntity.setCpf(personDTO.getCpf());
        personEntity.setBirthDate(personDTO.getBirthDate());
        personEntity.setPhones(phonesEntities);

        return personEntity;
    }

    //Retorno Entity
    public Person getPersonEntity(Long id){
        Optional<Person> optionalPerson = personRepository.findById(id);

        return optionalPerson.get();
    }

    //Retorno DTO
    public PersonDTO getPersonDTO(Long id) throws ParseException {

        Person personEntity = getPersonEntity(id);

        PersonDTO personDTO = new PersonDTO();

        personDTO = conversaoPersonDTO(personEntity, personDTO);

        return personDTO;
    }

    //Conversão de Entity para DTO
    public PersonDTO conversaoPersonDTO(Person personEntity, PersonDTO personDTO) throws ParseException {

        List<PhoneDTO> phonesDTO = new ArrayList<>();
        phonesDTO = phoneService.conversaoPhoneDTO(personEntity.getPhones(), phonesDTO) ;

        personDTO.setId(personEntity.getId());
        personDTO.setFirstName(personEntity.getFirstName());
        personDTO.setLastName(personEntity.getLastName());
        personDTO.setCpf(personEntity.getCpf());
        personDTO.setBirthDate(personEntity.getBirthDate());
        personDTO.setPhones(phonesDTO);

        return personDTO;
    }

    //Conversão de ListaDTO para ListaEntity
    private List<Person> conversaoPersonEntities(List<PersonDTO> personDTOS, List<Person> personEntities)
            throws ParseException {

        for (PersonDTO personDTO: personDTOS){
            Person personEntity = new Person();
            personEntity = conversaoPersonEntity(personDTO, personEntity);

            personEntities.add(personEntity);
        }

        return personEntities;
    }

    //Conversão de ListaEntity para ListaDTO
    private List<PersonDTO> conversaoPersonDTOs(List<Person> personEntities, List<PersonDTO> personDTOS)
            throws ParseException {

        for (Person personEntity: personEntities){
            PersonDTO personDTO = new PersonDTO();
            personDTO = conversaoPersonDTO(personEntity, personDTO);

            personDTOS.add(personDTO);
        }

        return personDTOS;
    }




}
