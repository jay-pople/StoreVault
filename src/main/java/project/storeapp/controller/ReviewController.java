package project.storeapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.storeapp.dto.product.ProductReviewRequestDTO;
import project.storeapp.dto.shop.ShopReviewRequestDTO;
import project.storeapp.service.reviewservice.ReviewService;

@RestController
public class ReviewController {

	@Autowired
	ReviewService service;
	
	@PostMapping("/shop/review")
	public ResponseEntity<?> saveShopReview(@RequestParam Long shopid,@RequestBody ShopReviewRequestDTO review ){
		return service.saveShopReview(shopid, review);
	}
	
	@PostMapping("/shop/product/review")
	public ResponseEntity<?> saveProductReview(@RequestParam("shopid") Long shopid ,@RequestParam("productid") Long productid, @RequestBody ProductReviewRequestDTO review ){
		return service.saveProductReview(shopid,productid,review);
	}
	
	@DeleteMapping("/shop/product/review")
	public ResponseEntity<?> deleteProductReview(@RequestParam("shopid") Long shopId ,@RequestParam("productid") Long productId, @RequestParam("reviewid") Long reviewId ){
		return service.deleteProductReview(shopId, productId, reviewId);
	}
}

