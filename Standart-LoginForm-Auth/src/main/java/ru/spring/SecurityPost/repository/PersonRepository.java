package ru.spring.SecurityPost.repository;

import ru.spring.SecurityPost.models.Person;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {

	public Optional<Person> findByLogin(String login);
	
}
