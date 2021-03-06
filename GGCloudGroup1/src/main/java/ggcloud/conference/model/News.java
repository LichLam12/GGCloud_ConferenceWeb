package ggcloud.conference.model;

import java.util.Date;

/*import java.sql.*;
*/
import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name = "news")
public class News {

	
	/*
	 * @GeneratedValue(strategy = GenerationType.AUTO)
	 */ 
	@Id
	private int id;
	private String title;
	private String openingline;
	private String writer;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	private Date publishday;
	private String image1;
	private String content1;
	private String image2;
	private String content2;

	public News() {
		super();
	}

	public News(int id, String title, String openingline, String writer, Date publishday, 
			String content1, String image1, String content2, String image2) {
		super();
		this.id = id;
		this.title = title;
		this.openingline = openingline;
		this.writer = writer;
		this.publishday = publishday;
		this.image1 = image1;
		this.content1 = content1;
		this.image2 = image2;
		this.content2 = content2;
	}

	public Date getPublishday() {
		return publishday;
	}

	public void setPublishday(Date publishday) {
		this.publishday = publishday;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOpeningline() {
		return openingline;
	}

	public void setOpeningline(String openingline) {
		this.openingline = openingline;
	}

	public String getImage1() {
		return image1;
	}

	public void setImage1(String image1) {
		this.image1 = image1;
	}

	public String getContent1() {
		return content1;
	}

	public void setContent1(String content1) {
		this.content1 = content1;
	}

	public String getImage2() {
		return image2;
	}

	public void setImage2(String image2) {
		this.image2 = image2;
	}

	public String getContent2() {
		return content2;
	}

	public void setContent2(String content2) {
		this.content2 = content2;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	


}
