package project.storeapp.service.shopsservice;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import project.storeapp.dto.product.ProductResponseDTO;
import project.storeapp.dto.product.ProductReviewResponseDTO;
import project.storeapp.dto.shop.ShopResponseDTO;
import project.storeapp.dto.shop.ShopReviewResponseDTO;
import project.storeapp.model.entity.Product;
import project.storeapp.model.entity.ProductReview;
import project.storeapp.model.entity.Shop;
import project.storeapp.model.entity.ShopReview;
import project.storeapp.repository.ShopRepository;

@Service
public class ShopFetchingService {

    @Autowired
    private ShopRepository shopRepo;

    public ResponseEntity<?> getAllShops() {
        List<Shop> shops = shopRepo.findAll();
        List<ShopResponseDTO> dtos = new ArrayList<>();

        for (Shop shop : shops) {
            ShopResponseDTO dto = new ShopResponseDTO();
            dto.setId(shop.getId());
            dto.setName(shop.getName());
            dto.setPhoneno(shop.getOwner().getPhoneNo());
            dtos.add(dto);
        }
        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    public ResponseEntity<?> getShop(Long id) {
        Optional<Shop> optionalShop = shopRepo.findById(id);
        if (optionalShop.isEmpty()) {
            Map<String,Object> response=new HashMap<>();
            response.put("message","Shop not found");
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        Shop shop = optionalShop.get();

        ShopResponseDTO shopResponseDTO = new ShopResponseDTO();
        shopResponseDTO.setId(shop.getId());
        shopResponseDTO.setName(shop.getName());
        shopResponseDTO.setPhoneno(shop.getOwner().getPhoneNo());

        // Set shop reviews
        List<ShopReviewResponseDTO> reviewDtosList = new ArrayList<>();
        for (ShopReview shopReview : shop.getReview()) {
            ShopReviewResponseDTO review = new ShopReviewResponseDTO();
            review.setUsername(shopReview.getActor().getUsername());
            review.setComment(shopReview.getComment());
            review.setRating(shopReview.getRating());
            reviewDtosList.add(review);
        }
        shopResponseDTO.setReviews(reviewDtosList);

        // Set products with their reviews
        List<ProductResponseDTO> productsDtoList = new ArrayList<>();
        for (Product product : shop.getProducts()) {
            ProductResponseDTO productDto = new ProductResponseDTO();
            productDto.setId(product.getId());
            productDto.setName(product.getName());
            productDto.setPrice(product.getPrice());
            productDto.setRating(product.getRating());
            productDto.setNoSells(product.getNoSells());
            productDto.setCategory(product.getCategory());

            List<ProductReviewResponseDTO> productResponseDtoList = new ArrayList<>();
            for (ProductReview review : product.getReview()) {
                ProductReviewResponseDTO reviewDto = new ProductReviewResponseDTO();
                reviewDto.setId(review.getId());
                reviewDto.setUsername(review.getActor().getUsername());
                reviewDto.setComment(review.getComment());
                reviewDto.setRating(review.getRating());
                productResponseDtoList.add(reviewDto);
            }

            productDto.setReview(productResponseDtoList);
            productsDtoList.add(productDto);   
        }

        shopResponseDTO.setProduct(productsDtoList);

        return new ResponseEntity<>(shopResponseDTO, HttpStatus.OK);
    }


}
