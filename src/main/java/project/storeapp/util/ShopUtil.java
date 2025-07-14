package project.storeapp.util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import project.storeapp.model.entity.Shop;
import project.storeapp.repository.ShopRepository;

@Component
public class ShopUtil {

    @Autowired
    private ShopRepository shopRepository;

    private static ShopUtil instance;

    @PostConstruct
    public void init() {
        instance = this;
    }

    public static Shop getShop(Long shopid) {
        Optional<Shop> shop = instance.shopRepository.findById(shopid);
        return shop.orElse(null);
    }
}
