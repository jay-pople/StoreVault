package project.storeapp.util;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import project.storeapp.model.entity.Product;
import project.storeapp.repository.ProductRepository;

@Component
public class ProductUtil {

    @Autowired
    private ProductRepository productRepository;

    private static ProductUtil instance;

    @PostConstruct
    public void init() {
        instance = this;
    }

    public static Product getProduct(Long productId) {
        Optional<Product> product = instance.productRepository.findById(productId);
        return product.orElse(null);
    }
}
