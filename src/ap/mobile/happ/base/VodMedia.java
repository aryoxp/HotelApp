package ap.mobile.happ.base;

public class VodMedia {
	public String id;
	public String name;
	public String description;
	public String stream;
	public String logo;
	public String icon;
	
	public VodMedia(String id, String name, String description, String stream, String logo, String icon) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.stream = stream;
		this.logo = logo;
		this.icon = icon;
	}
	
	public VodMedia() {}
}
