package project.storeapp.service.productservice;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import project.storeapp.dto.product.ProductResponseDTO;
import project.storeapp.dto.product.ProductReviewResponseDTO;
import project.storeapp.model.entity.Product;
import project.storeapp.model.entity.ProductReview;
import project.storeapp.model.entity.Shop;
import project.storeapp.repository.ShopRepository;

@Service
public class ProductFetchingService {

    @Autowired
    private ShopRepository shopRepo;

    public ResponseEntity<?> getProductsByShopId(Long shopId) {
        Optional<Shop> optionalShop = shopRepo.findById(shopId);
        if (optionalShop.isEmpty()) {
            return new ResponseEntity<>("Shop not found", HttpStatus.NOT_FOUND);
        }

        Shop shop = optionalShop.get();
        List<ProductResponseDTO> productDtos = new ArrayList<>();

        for (Product product : shop.getProducts()) {
            ProductResponseDTO productDto = new ProductResponseDTO();
            productDto.setId(product.getId());
            productDto.setName(product.getName());
            productDto.setNoSells(product.getNoSells());
            productDto.setRating(product.getRating());
            productDto.setCategory(product.getCategory());
            productDto.setPrice(product.getPrice());

            List<ProductReviewResponseDTO> reviewDtos = new ArrayList<>();
            for (ProductReview review : product.getReview()) {
                ProductReviewResponseDTO reviewDto = new ProductReviewResponseDTO();
                reviewDto.setId(review.getId());
                reviewDto.setUsername(review.getActor().getUsername());
                reviewDto.setRating(review.getRating());
                reviewDto.setComment(review.getComment());
                reviewDtos.add(reviewDto);
            }

            productDto.setReview(reviewDtos);
            productDtos.add(productDto);
        }

        return new ResponseEntity<>(productDtos, HttpStatus.OK);
    }
}

