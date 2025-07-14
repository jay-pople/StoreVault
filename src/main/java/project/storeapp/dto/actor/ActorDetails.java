package project.storeapp.dto.actor;

public class ActorDetails {

	
	private String phoneno;
	private String gmail;
	private String name;
	private Long id;
	
	
	public void setId(Long id) {
		this.id=id;
	}
	
	public Long getId() {
		return this.id;
	}
	
	public String getPhoneno() {
		return phoneno;
	}
	public void setPhoneno(String phoneno) {
		this.phoneno = phoneno;
	}
	public String getGmail() {
		return gmail;
	}
	public void setGmail(String gmail) {
		this.gmail = gmail;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
}
