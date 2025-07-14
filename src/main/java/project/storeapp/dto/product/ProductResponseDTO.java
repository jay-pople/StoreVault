package project.storeapp.dto.product;

import java.util.List;

import project.storeapp.model.entity.ProductReview;

public class ProductResponseDTO {

	    private Long id;
	    private String category;
	    private String name;
	    private double rating;
	    private int noSells;
	    private double price;
	    
	    private List<ProductReviewResponseDTO> review;
	    
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getCategory() {
			return category;
		}
		public void setCategory(String category) {
			this.category = category;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public double getRating() {
			return rating;
		}
		public void setRating(double rating) {
			this.rating = rating;
		}
		public int getNoSells() {
			return noSells;
		}
		public void setNoSells(int noSells) {
			this.noSells = noSells;
		}
		public List<ProductReviewResponseDTO> getReview() {
			return review;
		}
		public void setReview(List<ProductReviewResponseDTO> review) {
			this.review = review;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public double getPrice() {
			return this.price;
		}
		

		
	    
}
