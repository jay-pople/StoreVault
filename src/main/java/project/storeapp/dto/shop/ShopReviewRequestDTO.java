package project.storeapp.dto.shop;

public class ShopReviewRequestDTO {

	private Double rating;
	private String comment;
	private Long shopid;

	public Double getRating() {
		return rating;
	}
	public void setRating(Double rating) {
		this.rating = rating;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public Long getShopid() {
		return shopid;
	}
	public void setShopid(Long shopid) {
		this.shopid = shopid;
	}
	
	
	
}
