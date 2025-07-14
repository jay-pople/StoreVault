package project.storeapp.model.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String category;
    private String name;
    private double rating;
    private int noSells;
    private int stocks;
    private double price;
    

    @ManyToMany
    @JoinTable(
        name = "product_shop",
        joinColumns = @JoinColumn(name = "product_id"),
        inverseJoinColumns = @JoinColumn(name = "shop_id")
    )
    private List<Shop> shops;
    
    
    @OneToMany(mappedBy = "product")
    private List<ProductReview> review;
    
    // Getters and Setters

	public Long getId() {
		return id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public List<ProductReview> getReview() {
		return review;
	}

	public void setReview(List<ProductReview> review) {
		this.review = review;
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

	public int getStocks() {
		return stocks;
	}

	public void setStocks(int stocks) {
		this.stocks = stocks;
	}

	public void setId(Long id) {
		this.id = id;
	}



	public List<Shop> getShops() {
		return shops;
	}

	public void setShops(List<Shop> shops) {
		this.shops = shops;
	}

	public void setPrice(double price) {
		this.price=price;
	}
	public double getPrice() {
		return this.price;
	}

	
	


    
    
}
