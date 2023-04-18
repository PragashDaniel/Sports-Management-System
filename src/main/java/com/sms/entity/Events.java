package com.project.sms.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Transient;

@Entity
public class Events 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	Long meetid;
	public Events(Events addEvents, String fileName, String contentType, byte[] bytes) 
	{
		this.setMeetid(addEvents.meetid);
		this.setGame(addEvents.game);
		this.setCategory(addEvents.category);
		this.setStartdate(addEvents.startdate);
		this.setEnddate(addEvents.enddate);
		this.setLocation(addEvents.location);
		this.setOther(addEvents.other);
		this.setFileName(fileName);
		this.setFileType(contentType);
		this.event=bytes;
		
	}
	
	public Events() {}
	@Transient
    private String fileType;
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public Long getMeetid() {
		return meetid;
	}
	public void setMeetid(Long meetid) {
		this.meetid = meetid;
	}
	public String getGame() {
		return game;
	}
	public void setGame(String game) {
		this.game = game;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Date getStartdate() {
		return startdate;
	}
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}
	public Date getEnddate() {
		return enddate;
	}
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	String game;
	String category;
	Date startdate;
	Date enddate;
	@Lob
	byte[] event;
	public byte[] getEvent() {
		return event;
	}
	public void setEvent(byte[] event) {
		this.event = event;
	}
	String location;
	String other;
	String fileName;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	
}
