package project.storeapp.service.actorservice;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import project.storeapp.dto.actor.*;

@Service
public class AuthenticationService {
	
	@Autowired
	AuthenticationManager manager;
	
	@Autowired
	JwtService service;
	
	public ResponseEntity<?> signin(ActorDTO actorDto){
		
		ActorCredentials credentials = actorDto.getCredentials();
		Authentication auth=manager.authenticate(new UsernamePasswordAuthenticationToken(credentials.getUsername(),credentials.getPassword()));
		Map<String,String> response;
		if(auth.isAuthenticated()) {
			// send the JWT token 
			String token=service.generateToken(credentials.getUsername());
			
			
			response=new HashMap<String, String>();
			response.put("status", "successful");
			response.put("token", token);
			return new ResponseEntity<> (response,HttpStatus.OK);
		}
		
		response=new HashMap<String, String>();
		response.put("status", "failed");
		response.put("token", null);
		return new ResponseEntity<> (response,HttpStatus.BAD_REQUEST);
	}
}
