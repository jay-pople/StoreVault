package project.storeapp.service.productservice;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import project.storeapp.dto.product.ProductAddResponse;
import project.storeapp.dto.product.ProductRequestDTO;
import project.storeapp.model.entity.Actor;
import project.storeapp.model.entity.Owner;
import project.storeapp.model.entity.Product;
import project.storeapp.model.entity.Shop;
import project.storeapp.repository.ActorRepository;
import project.storeapp.repository.OwnerRepository;
import project.storeapp.repository.ProductRepository;
import project.storeapp.repository.ShopRepository;
import project.storeapp.service.validatorservice.ActorValidation;
import project.storeapp.service.validatorservice.ShopValidation;

@Service
public class ProductAdditionService {

    private final ProductRepository repo;
    private final ActorValidation actorValidation;
    private final ShopValidation shopValidation;
    private final OwnerRepository ownerRepository;
    private final ActorRepository actorRepository;
    private final ShopRepository shopRepository;

    @Autowired
    public ProductAdditionService(ProductRepository repo,
                                   ActorValidation actorValidation,
                                   ShopValidation shopValidation,
                                   OwnerRepository ownerRepository,
                                   ActorRepository actorRepository,
                                   ShopRepository shopRepository) {
        this.repo = repo;
        this.actorValidation = actorValidation;
        this.shopValidation = shopValidation;
        this.ownerRepository = ownerRepository;
        this.actorRepository=actorRepository;
        this.shopRepository=shopRepository;
    }

    public ResponseEntity<?> addProducts(Long shopId, List<ProductRequestDTO> products) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!actorValidation.isOwner(authentication)) {
            return new ResponseEntity<>("Permission denied: not an owner", HttpStatus.UNAUTHORIZED);
        }

        // Get Owner object using authenticated username
        String username = authentication.getName();
        Actor actor= actorRepository.findByUsername(username).get();
        Optional<Owner> result=ownerRepository.findById(actor.getId());

        if (result.isEmpty()) {
            return new ResponseEntity<>("Owner not found", HttpStatus.UNAUTHORIZED);
        }

        Owner owner = result.get();

        // Verify shop ownership
        if (!shopValidation.isOwnersShop(owner, shopId)) {
            return new ResponseEntity<>("You do not own this shop", HttpStatus.FORBIDDEN);
        }

        // Filter products into new and skipped
        List<ProductRequestDTO> newProducts = products.stream()
                .filter(p -> p.getId() == null)
                .collect(Collectors.toList());

        List<ProductRequestDTO> skippedProducts = products.stream()
                .filter(p -> p.getId() != null)
                .collect(Collectors.toList());

        
        Optional<Shop> optionalShop = shopRepository.findById(shopId);
        if (optionalShop.isEmpty()) {
            return new ResponseEntity<>("Shop not found", HttpStatus.NOT_FOUND);
        }
        Shop shop = optionalShop.get();        
        
        // Map to Product entities
        List<Product> entities = newProducts.stream().map(dto -> {
            Product product = new Product();
            product.setName(dto.getName());
            product.setCategory(dto.getCategory());
            product.setStocks(dto.getStock());
            product.setPrice(dto.getPrice());
            product.setShops(List.of(shop));
            return product;
        }).collect(Collectors.toList());

        if (!entities.isEmpty()) {
            repo.saveAll(entities);
        }

        String successMessage = entities.size() + " products added successfully.";
        String skippedMessage = skippedProducts.size() > 0
                ? skippedProducts.size() + " products were skipped: product having this id already exists, cannot overwrite."
                : "";

        ProductAddResponse response = new ProductAddResponse(successMessage, skippedMessage, skippedProducts);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
