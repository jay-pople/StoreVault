package project.storeapp.service.shopsservice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import project.storeapp.dto.shop.ShopRegistrationDTO;
import project.storeapp.model.entity.Owner;
import project.storeapp.model.entity.Shop;
import project.storeapp.repository.OwnerRepository;
import project.storeapp.repository.ShopRepository;

@Service
public class ShopRegistrationService {

    @Autowired
    private OwnerRepository ownerRepo;

    @Autowired
    private ShopRepository shopRepo;

    public ResponseEntity<?> registerShop(ShopRegistrationDTO shopDto) {
        Optional<Owner> ownerOpt = ownerRepo.findById(shopDto.getOwnerid());
        
        if (ownerOpt.isEmpty()) {
            return ResponseEntity.badRequest().body("Invalid Owner");
        }

        Owner owner = ownerOpt.get();

        // Create and save shop
        Shop shop = new Shop();
        shop.setName(shopDto.getShopname());
        shop.setOwner(owner);

        Shop savedShop = shopRepo.save(shop);

        // Prepare response
        ShopRegistrationDTO response = new ShopRegistrationDTO();
        response.setShopid(savedShop.getId());
        response.setShopname(savedShop.getName());
        response.setOwnerid(owner.getId());

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
