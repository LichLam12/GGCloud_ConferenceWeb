package ggcloud.conference.model;

import java.util.Date;

import javax.persistence.Entity;
/*import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;*/
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity(name = "eventtable")
public class Event {
	
	@Id
	private int id;
    private String eventname;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private Date eventdate;
    private String eventtime;
    private String eventlocation;
    private String lectureravatar;
    private String lecturername;
    private String benefit;
    
	public Event() {
		super();
	}

	public Event(int id, String eventname, Date eventdate, String eventtime, String eventlocation,
			String lectureravatar, String lecturername, String benefit) {
		super();
		this.id = id;
		this.eventname = eventname;
		this.eventdate = eventdate;
		this.eventtime = eventtime;
		this.eventlocation = eventlocation;
		this.lectureravatar = lectureravatar;
		this.lecturername = lecturername;
		this.benefit = benefit;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEventname() {
		return eventname;
	}

	public void setEventname(String eventname) {
		this.eventname = eventname;
	}

	public Date getEventdate() {
		return eventdate;
	}

	public void setEventdate(Date eventdate) {
		this.eventdate = eventdate;
	}

	public String getEventtime() {
		return eventtime;
	}

	public void setEventtime(String eventtime) {
		this.eventtime = eventtime;
	}

	public String getEventlocation() {
		return eventlocation;
	}

	public void setEventlocation(String eventlocation) {
		this.eventlocation = eventlocation;
	}

	public String getLectureravatar() {
		return lectureravatar;
	}

	public void setLectureravatar(String lectureravatar) {
		this.lectureravatar = lectureravatar;
	}

	public String getLecturername() {
		return lecturername;
	}

	public void setLecturername(String lecturername) {
		this.lecturername = lecturername;
	}

	public String getBenefit() {
		return benefit;
	}

	public void setBenefit(String benefit) {
		this.benefit = benefit;
	} 
	
	
}
