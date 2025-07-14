package project.storeapp.dto.shop;

import java.util.List;

import project.storeapp.dto.product.ProductResponseDTO;

public class ShopResponseDTO {

	private Long id;
	private String phoneno;
	private String name;
	private List<ProductResponseDTO> product;
	private List<ShopReviewResponseDTO> reviews;
	
	
	
	public List<ShopReviewResponseDTO> getReviews() {
		return reviews;
	}
	public void setReviews(List<ShopReviewResponseDTO> reviews) {
		this.reviews = reviews;
	}
	public List<ProductResponseDTO> getProduct() {
		return product;
	}
	public void setProduct(List<ProductResponseDTO> product) {
		this.product = product;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
