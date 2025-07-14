package project.storeapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.storeapp.dto.product.ProductRequestDTO;
import project.storeapp.service.productservice.ProductService;

@RestController

@RequestMapping("/product")
public class ProductController {

	
	@Autowired
	private ProductService service;
	
	@PostMapping("/add")
	public ResponseEntity<?> addProducts(@RequestParam("shopid") Long shopId,@RequestBody List<ProductRequestDTO> products){
		return service.addProducts(shopId,products); 
	}
	
	@PostMapping("/update")
	public ResponseEntity<?> updateProducts(@RequestParam("shopid") Long shopId,@RequestBody List<ProductRequestDTO> products){
		return service.updateProducts(shopId,products); 
	}
	
	@GetMapping("/all")
	public ResponseEntity<?> getProducts(@RequestParam("shopid") Long shopId){
		return service.getProducts(shopId);
	}
	
	
}