package project.storeapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import project.storeapp.dto.actor.*;
import project.storeapp.service.actorservice.AuthenticationService;

@RestController
public class AuthenticationController {

	
	@Autowired
	AuthenticationService service;
	
	@PostMapping("/signin")
	public ResponseEntity<?> signin(@RequestBody ActorDTO body){
		return service.signin(body);
	}
}
