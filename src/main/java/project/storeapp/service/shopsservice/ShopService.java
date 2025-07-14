package project.storeapp.service.shopsservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import project.storeapp.dto.actor.*;
import project.storeapp.dto.shop.ShopRegistrationDTO;

@Service
public class ShopService {
	
	@Autowired
	private ShopRegistrationService regService;
	
	@Autowired
	private ShopFetchingService fetchService;
	
	public ResponseEntity<?> register(ShopRegistrationDTO shopDto){
		return regService.registerShop(shopDto);
	}
	
	public ResponseEntity<?> getShops(){
		return fetchService.getAllShops();
	}

	public ResponseEntity<?> getShop(Long id) {
		return fetchService.getShop(id);
	}
	
	
}
