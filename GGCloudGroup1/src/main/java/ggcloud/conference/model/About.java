package ggcloud.conference.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "about")
public class About {
	
	@Id
	private int id;
	private String content;
	private String image;
	
	public About() {
		super();
	}

	public About(int id, String content, String image) {
		super();
		this.id = id;
		this.content = content;
		this.image = image;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}
	
	
}
