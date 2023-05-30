package ru.spring.H2Test.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import ru.spring.H2Test.models.Person;

public interface PersonRepository extends JpaRepository<Person, Long> {

	public Optional<Person> findByLogin(String login);
	
}
