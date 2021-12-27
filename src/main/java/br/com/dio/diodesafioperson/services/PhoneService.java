package br.com.dio.diodesafioperson.services;

import br.com.dio.diodesafioperson.dto.PhoneDTO;
import br.com.dio.diodesafioperson.entities.Phone;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneService {

    //conversão DTO para Entity
    public Phone conversaoPhoneEntity(PhoneDTO phoneDTO, Phone phoneEntity){

        phoneEntity.setId(phoneDTO.getId());
        phoneEntity.setType(phoneDTO.getType());
        phoneEntity.setNumber(phoneDTO.getNumber());

        return phoneEntity;
    }

    //conversão Entity para DTO
    public PhoneDTO conversaoPhoneDTO(Phone phoneEntity, PhoneDTO phoneDTO){

        phoneDTO.setId(phoneEntity.getId());
        phoneDTO.setType(phoneEntity.getType());
        phoneDTO.setNumber(phoneEntity.getNumber());

        return phoneDTO;
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

    //conversão ListaDTO para ListaEntity
    public List<PhoneDTO> conversaoPhoneDTO(List<Phone> phoneEntities, List<PhoneDTO> phoneDTOs){

        for(Phone phoneEntity : phoneEntities){
            PhoneDTO phoneDTO = new PhoneDTO();
            phoneDTO = conversaoPhoneDTO(phoneEntity, phoneDTO);

            phoneDTOs.add(phoneDTO);
        }

        return phoneDTOs;
    }

}
