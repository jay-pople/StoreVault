package project.storeapp.service.reviewservice.product;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import project.storeapp.model.entity.Product;
import project.storeapp.model.entity.ProductReview;
import project.storeapp.model.entity.Shop;
import project.storeapp.repository.ProductReviewRepository;
import project.storeapp.util.ActorUtil;
import project.storeapp.util.ProductUtil;
import project.storeapp.util.ShopUtil;

@Service
public class DeleteProductReviewService {

	@Autowired
	ProductReviewRepository repository;
	public ResponseEntity<?> deleteProductReview(Long shopId,Long productId,Long reviewId){
		
		Shop shop=ShopUtil.getShop(shopId);
		
		if(shop==null) {
			return new ResponseEntity("shop with provided is not found",HttpStatus.NOT_FOUND);
		}
		
		
		Product product= ProductUtil.getProduct(productId);
		
		Optional<ProductReview> result= repository.findById(reviewId);
		
		
		if(result.isPresent()) {
			
			ProductReview review=result.get();
			
			String actorUsername = review.getActor().getUsername();
			String authUserUsername=ActorUtil.getUsername();
			
			if(actorUsername.equals(authUserUsername)) {
				repository.deleteById(reviewId);
				
				return new ResponseEntity("Review Deleted Successfully",HttpStatus.NO_CONTENT);
			}
			else {
				return new ResponseEntity("cannot delete else's review",HttpStatus.FORBIDDEN);
			}
			
		}
		return new ResponseEntity("no comment with this id found",HttpStatus.NOT_FOUND);
		
		
	}
}
