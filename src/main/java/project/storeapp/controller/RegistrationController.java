package project.storeapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import project.storeapp.dto.actor.*;
import project.storeapp.dto.shop.ShopRegistrationDTO;
import project.storeapp.service.actorservice.*;
import project.storeapp.service.shopsservice.ShopService;

@RestController
public class RegistrationController {

	@Autowired
	RegistrationService service;
	
	@Autowired
	ShopService shopService;
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup(@RequestBody ActorDTO actorDto){
		return service.signup(actorDto);
	}
	
	@PostMapping("shop/register")
	public ResponseEntity<?> register(@RequestBody ShopRegistrationDTO shopDto){
		return shopService.register(shopDto);
	}
}
