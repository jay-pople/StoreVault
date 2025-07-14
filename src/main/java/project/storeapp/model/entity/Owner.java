package project.storeapp.model.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Owner {
    @Id
    @GeneratedValue
    private Long id; // same as actor_id

    private String name;
    private String gmail;
    private String phoneNo;
    
    @OneToMany(mappedBy = "owner")
    private List<Shop> shops;

    @OneToOne
    @MapsId
    @JoinColumn(name = "id")
    private Actor actor;

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGmail() {
		return gmail;
	}

	public void setGmail(String gmail) {
		this.gmail = gmail;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	public List<Shop> getShops() {
		return shops;
	}

	public void setShops(List<Shop> shops) {
		this.shops = shops;
	}


    
}
