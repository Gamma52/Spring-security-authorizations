package ru.spring.H2Test.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ru.spring.H2Test.JWTUtil;


@Component
public class JWTFilter extends OncePerRequestFilter{
	
	 @Autowired
	 private JWTUtil jwtUtil; 

	 
	 @Override
	 protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
	            throws ServletException, IOException, java.io.IOException {
		 final String authorizationHeader = request.getHeader("Authorization");
		 String username = null;
		 String jwt = null;
		 if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			 jwt = authorizationHeader.substring(7);
		     username = jwtUtil.extractUsername(jwt);
		  }
		  if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			  String commaSeparatedListOfAuthorities = jwtUtil.extractAuthorities(jwt);
		      List<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(commaSeparatedListOfAuthorities);
		      UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
		    		  new UsernamePasswordAuthenticationToken(
		                            username, null, authorities);
		      SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		   }
		      chain.doFilter(request, response);
	 }

}
