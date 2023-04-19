package com.project.sms.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Transient;

@Entity
public class WinnerPanel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Transient
	private String fileType;
	private String game;
	private String category;
	private String prize;
	private String location;
	private Date date;
	private String info;
	@Lob
	private byte[] photo;
	private String fileName;

	public WinnerPanel() {}

	public WinnerPanel(WinnerPanel wp, String fileName1, String contentType, byte[] bytes) {
		this.setId(wp.id);
		this.setGame(wp.game);
		this.setCategory(wp.category);
		this.setPrize(wp.prize);
		this.setLocation(wp.location);
		this.setDate(wp.date);
		this.setInfo(wp.info);
		this.fileName = fileName1;
		this.fileType = contentType;
		this.photo = bytes;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public String getPrize() {
		return prize;
	}

	public void setPrize(String prize) {
		this.prize = prize;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}


	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

}
