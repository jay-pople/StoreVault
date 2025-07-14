package project.storeapp.service.reviewservice.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import project.storeapp.dto.product.ProductReviewRequestDTO;
import project.storeapp.model.entity.Actor;
import project.storeapp.model.entity.Product;
import project.storeapp.model.entity.ProductReview;
import project.storeapp.model.entity.Shop;
import project.storeapp.repository.ActorRepository;
import project.storeapp.repository.ProductReviewRepository;
import project.storeapp.util.ActorUtil;
import project.storeapp.util.ProductUtil;
import project.storeapp.util.ShopUtil;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductReviewService {

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private ProductReviewRepository productReviewRepository;

    public ResponseEntity<?> saveReview(Long shopId, Long productId, ProductReviewRequestDTO productDto) {
        Actor actor = ActorUtil.getActor(actorRepository);
        Shop shop = ShopUtil.getShop(shopId);
        Product product = ProductUtil.getProduct(productId);

        if (actor == null || shop == null || product == null) {
            return new ResponseEntity<>("Invalid request", HttpStatus.BAD_REQUEST);
        }

        ProductReview review = new ProductReview();
        review.setActor(actor);
        review.setProduct(product);
        review.setRating(productDto.getRating());
        review.setComment(productDto.getComment());
        review.setShop(shop);

        ProductReview result = productReviewRepository.save(review);

        Map<String,Object> response=new HashMap<>();
        if (result != null) {
            response.put("message","review saved successfully");
            response.put("review",review);
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } else {
            response.put("message","failed to save review");
            response.put("review",null);
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
