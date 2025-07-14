package project.storeapp.service.productservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import project.storeapp.dto.product.ProductRequestDTO;

@Service
public class ProductService {

	@Autowired
	private ProductFetchingService fetchService;
	
	@Autowired
	private ProductAdditionService addService;
	
	@Autowired
	private ProductUpdateService updateService;
	
	public ResponseEntity<?> addProducts(Long shopId, List<ProductRequestDTO> products){
		
		return addService.addProducts(shopId, products);
	}
	
	public ResponseEntity<?> updateProducts(Long shopId, List<ProductRequestDTO> products){	
		return updateService.updateProducts(shopId, products);
	}

	public ResponseEntity<?> getProducts(Long shopId) {
		return fetchService.getProductsByShopId(shopId);
	}
	
}
