package project.storeapp.service.productservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.lang.Objects;
import project.storeapp.dto.product.ProductRequestDTO;
import project.storeapp.model.entity.Actor;
import project.storeapp.model.entity.Owner;
import project.storeapp.model.entity.Product;
import project.storeapp.repository.ActorRepository;
import project.storeapp.repository.OwnerRepository;
import project.storeapp.repository.ProductRepository;
import project.storeapp.service.validatorservice.ActorValidation;
import project.storeapp.service.validatorservice.ShopValidation;

@Service
public class ProductUpdateService {
	
	@Autowired
	ProductRepository repository;
	
	@Autowired
	ActorValidation actorValidation;
	
	@Autowired
	ShopValidation shopValidation;
	
	@Autowired
	ActorRepository actorRepository;
	
	@Autowired
	OwnerRepository ownerRepository;
	

	public ResponseEntity<?> updateProducts(Long shopId,List<ProductRequestDTO> productDtoList) {
		
		
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		
		if(!actorValidation.isOwner(auth)) {
			return  new ResponseEntity(HttpStatus.UNAUTHORIZED);
		}
		
		Actor actor=actorRepository.findByUsername(auth.getName()).get();
		
		Owner owner =ownerRepository.findById(actor.getId()).get(); 
		
		if(!shopValidation.isOwnersShop(owner, shopId)){
			return new ResponseEntity("shop does not belongs to current owner",HttpStatus.UNAUTHORIZED);
		}
		
		
	    // 1. Extract IDs from DTOs
	    List<Long> ids = productDtoList.stream()
	                                   .map(ProductRequestDTO::getId)
	                                   .filter(java.util.Objects::nonNull)
	                                   .collect(Collectors.toList());

	    // 2. Fetch all existing products in one go
	    List<Product> existingProducts = repository.findAllById(ids);

	    // 3. Convert List<Product> to Map<id, Product> for fast lookup
	    java.util.Map<Long, Product> productMap = existingProducts.stream()
	        .collect(Collectors.toMap(Product::getId, p -> p));

	    // 4. Prepare lists for tracking success/failure
	    List<ProductRequestDTO> updatedProducts = new ArrayList<>();
	    List<ProductRequestDTO> deniedProducts = new ArrayList<>();

	    // 5. Match DTOs with existing entities and apply updates
	    for (ProductRequestDTO dto : productDtoList) {
	        if (dto.getId() == null || !productMap.containsKey(dto.getId())) {
	            deniedProducts.add(dto); // Product not found or ID missing
	            continue;
	        }

	        Product product = productMap.get(dto.getId());

	        product.setName(dto.getName());
	        product.setPrice(dto.getPrice());
	        product.setStocks(product.getStocks() + dto.getStock()); // Adjust accordingly

	        updatedProducts.add(dto); // Record that we updated it
	    }

	    // 6. Save all updated products in one go
	    repository.saveAll(existingProducts);

	    // 7. Return update report
	    java.util.Map<String, Object> response = new java.util.HashMap<>();
	    response.put("updated", updatedProducts);
	    response.put("denied", deniedProducts);

	    return ResponseEntity.ok(response);
	}


}
