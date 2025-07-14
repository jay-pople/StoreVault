package project.storeapp.service.reviewservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import project.storeapp.dto.product.ProductReviewRequestDTO;
import project.storeapp.dto.shop.ShopReviewRequestDTO;
import project.storeapp.service.reviewservice.product.DeleteProductReviewService;
import project.storeapp.service.reviewservice.product.ProductReviewService;
import project.storeapp.service.reviewservice.shop.ShopReviewService;

@Service
public class ReviewService {

	@Autowired
	private ShopReviewService shopReviewService;
	
	@Autowired
	private ProductReviewService productReviewService;
	
	@Autowired
	private DeleteProductReviewService deleteProductReviewService;
	
		public ResponseEntity<?> saveShopReview(Long shopid, ShopReviewRequestDTO review){
		return shopReviewService.saveShopReview(shopid,review);}
	
		public ResponseEntity<?> saveProductReview(Long shopId,Long productId,ProductReviewRequestDTO review){
		return productReviewService.saveReview(shopId,productId,review);}
		
		public ResponseEntity<?> deleteProductReview(Long shopId,Long productId,Long reviewId){
		return deleteProductReviewService.deleteProductReview(shopId, productId, reviewId);}
}
