package project.storeapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import project.storeapp.model.entity.ShopReview;

@Repository
public interface ShopReviewRepository extends JpaRepository<ShopReview, Long> {

}
