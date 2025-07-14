package project.storeapp.model.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Shop {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;
   
    
   @ManyToMany (mappedBy = "shops")
   private List<Product> products;
   
   @OneToMany(mappedBy = "shop")
   private List<ShopReview> review;
   
	public List<ShopReview> getReview() {
	return review;
}

	public void setReview(List<ShopReview> review) {
	this.review = review;
}

public void setId(Long id) {
	this.id = id;
}

	public Long getId() {
		return id;
	}
	
	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Owner getOwner() {
		return owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

    // Getters and Setters
    
    
}
