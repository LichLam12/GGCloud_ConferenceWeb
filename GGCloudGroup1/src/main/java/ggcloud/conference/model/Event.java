package ggcloud.conference.model;

import java.sql.Time;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "event")
public class Event {
	
	@Id
	private int id;
    private String eventName;
    private Date eventDate;
    private Time eventTime;
    private String eventLocation;
    private String lecturerAvatar;
    private String lecturerName;
    
	public Event() {
		super();
	}

	public Event(int id, String eventName, Date eventDate, Time eventTime, String eventLocation, String lecturerAvatar,
			String lecturerName) {
		super();
		this.id = id;
		this.eventName = eventName;
		this.eventDate = eventDate;
		this.eventTime = eventTime;
		this.eventLocation = eventLocation;
		this.lecturerAvatar = lecturerAvatar;
		this.lecturerName = lecturerName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public Time getEventTime() {
		return eventTime;
	}

	public void setEventTime(Time eventTime) {
		this.eventTime = eventTime;
	}

	public String getEventLocation() {
		return eventLocation;
	}

	public void setEventLocation(String eventLocation) {
		this.eventLocation = eventLocation;
	}

	public String getLecturerAvatar() {
		return lecturerAvatar;
	}

	public void setLecturerAvatar(String lecturerAvatar) {
		this.lecturerAvatar = lecturerAvatar;
	}

	public String getLecturerName() {
		return lecturerName;
	}

	public void setLecturerName(String lecturerName) {
		this.lecturerName = lecturerName;
	}
    
    
    
}
