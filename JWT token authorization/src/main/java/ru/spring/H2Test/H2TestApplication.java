package ru.spring.H2Test;

import org.h2.server.web.WebServlet;
import  org.springframework.boot.autoconfigure.h2.H2ConsoleAutoConfiguration;
//import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import jakarta.servlet.http.HttpServlet;

@SpringBootApplication
public class H2TestApplication {

	public static void main(String[] args) {
		SpringApplication.run(H2TestApplication.class, args);
	}
//	@Bean
//    ServletRegistrationBean h2servletRegistration(){
//        ServletRegistrationBean registrationBean = new ServletRegistrationBean( new WebServlet());
//        registrationBean.addUrlMappings("/console/*");
//        return registrationBean;
//    }
//    @Bean
//    ServletRegistrationBean h2servletRegistration(){
//        ServletRegistrationBean<org.h2.server.web.WebServlet> registration = new ServletRegistrationBean<>();
//        registration.setServlet(new WebServlet());
//        registration.addUrlMappings("/h2-console/*");
//        registration.addInitParameter("webAllowOthers", "true");
//        registration.addInitParameter("webPort", "7777");// <-- the port your wish goes here
//
//        return registration;
//    }

}
