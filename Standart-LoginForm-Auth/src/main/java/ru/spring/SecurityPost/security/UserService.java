package ru.spring.SecurityPost.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import ru.spring.SecurityPost.models.Person;
import ru.spring.SecurityPost.repository.PersonRepository;

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
