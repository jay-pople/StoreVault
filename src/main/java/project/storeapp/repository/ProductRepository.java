package project.storeapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.storeapp.model.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
