package project.storeapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import project.storeapp.model.entity.Product;
import project.storeapp.service.productservice.ProductFetchingService;
import project.storeapp.service.shopsservice.ShopFetchingService;


@RestController
public class ShopController {

    @Autowired
    private ShopFetchingService shopService;

    @Autowired
    private ProductFetchingService productService;

    @GetMapping("/shops")
    public ResponseEntity<?> getAllShops() {
        return shopService.getAllShops();
    }

    @GetMapping("/shop")
    public ResponseEntity<?> getShop(@RequestParam("id") Long id) {
        return shopService.getShop(id);
    }

    @GetMapping("/shop/products")
    public ResponseEntity<?> getShopProducts(@RequestParam("id") Long id) {
        return productService.getProductsByShopId(id);
    }
       
}
