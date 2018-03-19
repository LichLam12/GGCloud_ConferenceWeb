package ggcloud.conference.model;

/*import java.sql.*;
*/
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "news")
public class News {

	
	/*
	 * @GeneratedValue(strategy = GenerationType.AUTO)
	 */ 
	@Id
	private int id;
	private String content;

	public News() {
		super();
	}

	public News(int id, String content) {
		super();
		this.id = id;
		this.content = content;
	}

	public News(String content) {
		this.content = content;
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

}
