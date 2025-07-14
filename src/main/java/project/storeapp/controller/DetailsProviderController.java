package project.storeapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import project.storeapp.dto.actor.*;
import project.storeapp.service.actorservice.DetailsProviderService;

@RestController
public class DetailsProviderController {

	@Autowired
	private DetailsProviderService service;
	@GetMapping("/details")
	public ResponseEntity<?> getDetails(){
		return service.getDetails();
	}
	
	@PutMapping("/updatedetails")
	public ResponseEntity<?> updateDetails(@RequestBody ActorDTO actorDto){
		System.out.println(actorDto.getCredentials());
		return service.updateDetails(actorDto);
	}
}
