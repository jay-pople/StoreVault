package project.storeapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import project.storeapp.model.entity.Shop;

public interface ShopRepository extends JpaRepository<Shop, Long> {

}
