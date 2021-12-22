package br.com.dio.diodesafioperson.repositories;

import br.com.dio.diodesafioperson.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
