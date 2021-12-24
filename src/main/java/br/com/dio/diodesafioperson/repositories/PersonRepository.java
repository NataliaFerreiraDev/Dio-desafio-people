package br.com.dio.diodesafioperson.repositories;

import br.com.dio.diodesafioperson.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PersonRepository extends JpaRepository<Person, Long> {

    List<Person> findByCpf(String cpf);

}
