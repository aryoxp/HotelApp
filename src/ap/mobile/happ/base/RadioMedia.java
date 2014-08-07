package ap.mobile.happ.base;

public class RadioMedia {
	public String id;
	public String name;
	public String description;
	public String file;
	public String stream;
	public String logo;
	
	public RadioMedia(String name, String description, String file, String stream, String logo) {
		this.name = name;
		this.description = description;
		this.file = file;
		this.stream = stream;
		this.logo = logo;
	}
	
	public RadioMedia(){
		
	}
}
