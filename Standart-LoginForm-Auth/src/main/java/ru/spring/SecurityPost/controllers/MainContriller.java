package ru.spring.SecurityPost.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainContriller {
	
	// Public 
	@GetMapping("/")
	public String showPublicInfo() {
		return "Hello visitor!";
	}
	
	
	// Only for users
	@GetMapping("/user")
	public String showPrivateInfo() {
		return "Hello User!";
	}
	
	
	// Only for Admin
	@GetMapping("/admin")
	public String showSecretInfo() {
		return "Hello Admin!";
	}

}
