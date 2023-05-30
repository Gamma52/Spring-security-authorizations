package ru.spring.H2Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import ru.spring.H2Test.security.AuthRequest;
import ru.spring.H2Test.security.AuthResponse;


@RestController
public class MainController {
	
	@Autowired
	private AuthenticationProvider authenticationProvider;

	@Autowired
	private JWTUtil jwtTokenUtil;
	

	@GetMapping("/helo")
	public String helo() {	
		return "Helloooo everyone!!!";
    }
	
    // сюда доступ разрешен только user и admin 
    @GetMapping("/user")
    public String user() {
        return "User";
    }
    
    
    // сюда доступ разрешен только admin 
    @GetMapping("/admin")
    public String admin() {
        return "Admin";
    }
    
    
    @PostMapping("/authenticate")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse createAuthenticationToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication;
        try {        	
        	authentication = authenticationProvider.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getName(), authRequest.getPassword()));            
            System.out.println(authentication);
        } catch (BadCredentialsException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Имя или пароль неправильны", e);
        }
        // при создании токена в него кладется username как Subject claim и список authorities как кастомный claim
        String jwt = jwtTokenUtil.generateToken((UserDetails) authentication.getPrincipal());
        return new AuthResponse(jwt);
    }
	
}
