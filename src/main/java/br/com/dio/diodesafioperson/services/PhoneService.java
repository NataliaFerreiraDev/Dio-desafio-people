package br.com.dio.diodesafioperson.services;

import br.com.dio.diodesafioperson.dto.PhoneDTO;
import br.com.dio.diodesafioperson.entities.Phone;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneService {

    //conversão DTO para Entity
    public Phone conversaoPhoneEntity(PhoneDTO phoneDTO, Phone phoneEntity){

        phoneEntity.setType(phoneDTO.getType());
        phoneEntity.setNumber(phoneDTO.getNumber());

        return phoneEntity;
    }

    //conversão ListaDTO para ListaEntity
    public List<Phone> conversaoPhoneEntities(List<PhoneDTO> phonesDTO, List<Phone> phoneEntities){

        for(PhoneDTO phone : phonesDTO){
            Phone phoneEntity = new Phone();
            phoneEntity = conversaoPhoneEntity(phone, phoneEntity);

            phoneEntities.add(phoneEntity);
        }

        return phoneEntities;
    }

}
