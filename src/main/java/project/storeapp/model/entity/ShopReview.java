package project.storeapp.model.entity;

import jakarta.persistence.*;

@Entity
public class ShopReview {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne
    private Actor actor; // Alternatively use ManyToOne with Actor
    
    

    private double rating;
    private String comment;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private Shop shop;
    
    

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public void setReviewId(Long reviewId) {
		this.reviewId = reviewId;
	}

	public Long getReviewId() {
		return reviewId;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Shop getShop() {
		return shop;
	}

	public void setShop(Shop shop) {
		this.shop = shop;
	}

    // Getters and Setters
    
    
}
