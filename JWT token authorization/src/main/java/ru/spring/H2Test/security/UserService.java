package ru.spring.H2Test.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import ru.spring.H2Test.models.Person;
import ru.spring.H2Test.repository.PersonRepository;

@Component
public class UserService implements UserDetailsService {
	
	@Autowired
    private PersonRepository personRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Person user = personRepository.findByLogin(username)
        		.orElseThrow(() -> new UsernameNotFoundException("Unknown user: "+username));
                     
        UserDetails userDetails = User.builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .roles(user.getRole())
                .build();        
        
        return userDetails;
    }
}
