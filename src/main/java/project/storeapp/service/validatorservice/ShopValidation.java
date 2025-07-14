package project.storeapp.service.validatorservice;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import project.storeapp.model.entity.Owner;
import project.storeapp.model.entity.Shop;
import project.storeapp.repository.ShopRepository;

@Service
public class ShopValidation {

    @Autowired
    private ShopRepository shopRepository;

    public boolean isOwnersShop(Owner owner, Long shopId) {
        Optional<Shop> optionalShop = shopRepository.findById(shopId);

        if (optionalShop.isPresent()) {
            Shop shop = optionalShop.get();
            Owner shopOwner = shop.getOwner();

            return shopOwner != null && shopOwner.equals(owner); // Object equality
        }

        return false;
    }
}
