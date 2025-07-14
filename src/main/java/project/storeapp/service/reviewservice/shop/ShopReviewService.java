package project.storeapp.service.reviewservice.shop;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import project.storeapp.dto.shop.ShopReviewRequestDTO;
import project.storeapp.dto.shop.ShopReviewResponseDTO;
import project.storeapp.model.entity.Actor;
import project.storeapp.model.entity.Shop;
import project.storeapp.model.entity.ShopReview;
import project.storeapp.repository.ActorRepository;
import project.storeapp.repository.ShopRepository;
import project.storeapp.repository.ShopReviewRepository;
import project.storeapp.util.ActorUtil;
import project.storeapp.util.ShopUtil;

@Service
public class ShopReviewService {

	@Autowired
	ShopReviewRepository reviewRepository;
	
	@Autowired
	ShopRepository shopRepository;
	
	@Autowired
	ActorRepository actorRepository;
	
	
	
//	public ResponseEntity<?> saveShopReview(ShopReviewRequestDTO shopReview){
//	
//		ShopReview review=new ShopReview();
//		
//		String username = SecurityContextHolder.getContext().getAuthentication().getName();
//		
//		Optional<Actor> actor=actorRepository.findByUsername(username);
//		
//		if(!actor.isPresent()) {
//			return new ResponseEntity<String>("User not found",HttpStatus.UNAUTHORIZED);
//		}
//		
//		review.setActor(actor.get());
//		review.setComment(shopReview.getComment());
//		review.setRating(shopReview.getRating());
//		
//		
//		
//		Optional<Shop> result= shopRepository.findById(shopReview.getShopid());
//		if(result.isPresent()) {
//			review.setShop(result.get());
//			reviewRepository.save(review);
//			return new ResponseEntity<String>("review saved succesfully",HttpStatus.OK);
//		}
//		return new ResponseEntity<String>("shop not found",HttpStatus.BAD_REQUEST);
//	}
	
	public ResponseEntity<?> saveShopReview(Long shopid,ShopReviewRequestDTO reviewDto){
		
		// get username
		Actor actor = ActorUtil.getActor(actorRepository);		
		 Shop shop = ShopUtil.getShop(shopid);
		 
		 if(shop!=null && actor!=null) {
			 ShopReview review=new ShopReview();
			 review.setRating(reviewDto.getRating());
			 review.setComment(reviewDto.getComment());
			 review.setActor(actor);
			 review.setShop(shop);
			 
			 ShopReview result= reviewRepository.save(review);
			 
			 if(result!=null) {
				 return new ResponseEntity<String>("review saved successfully",HttpStatus.CREATED);
			 }
		 }
		 return new ResponseEntity<String>("invalid shop id",HttpStatus.BAD_REQUEST);
		
		
	}
}
