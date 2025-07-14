package project.storeapp.dto.shop;



public class ShopRegistrationDTO {

	private Long shopid; 
	
	  private Long ownerid;
	  
	  private String shopname;

	public Long getOwnerid() {
		return ownerid;
	}
	
	public Long getShopid() {
		return shopid;
	}

	public void setShopid(Long shopid) {
		this.shopid = shopid;
	}

	public void setOwnerid(Long ownerid) {
		this.ownerid = ownerid;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}
	  
	 
	    
	    
	    
	    

}
