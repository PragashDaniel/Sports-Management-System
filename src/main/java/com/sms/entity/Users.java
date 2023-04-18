package com.project.sms.entity;

import java.sql.Date;

import javax.persistence.Entity;

import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Transient;

@Entity
public class Users {

	@Id
	private Long id;
	private String name;
	private String fatherName;
	private Date dob;
	private int age;
	private String address;
	private Long mobileNo;
	private String degree;
	private String game;
	private String experience;
	private String fileName1;
	private String fileName2;
	private String fileName3;
	private String status;
	private String gender;
	@Transient
	private String fileType1;
	@Transient
	private String fileType2;
	@Transient
	private String fileType3;
	@Lob
	private byte[] data1;
	@Lob
	private byte[] data2;
	@Lob
	private byte[] data3;

	public Users(Users attachmentModel, String fileName1, String fileName2, String fileName3, String fileType1,
			String fileType2, String fileType3, byte[] data1, byte[] data2, byte[] data3, String status) {
		this.setId(attachmentModel.id);
		this.setName(attachmentModel.name);
		this.setFatherName(attachmentModel.fatherName);
		this.setDob(attachmentModel.dob);
		this.setAge(attachmentModel.age);
		this.setGender(attachmentModel.gender);
		this.setAddress(attachmentModel.address);
		this.setMobileNo(attachmentModel.mobileNo);
		this.setDegree(attachmentModel.degree);
		this.setGame(attachmentModel.game);
		this.setExperience(attachmentModel.experience);
		this.setFileName1(fileName1);
		this.setFileName2(fileName2);
		this.setFileName3(fileName3);
		this.setFileType1(fileType1);
		this.setFileType2(fileType2);
		this.setFileType3(fileType3);
		this.data1 = data1;
		this.data2 = data2;
		this.data3 = data3;
		this.setStatus(status);
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFatherName() {
		return fatherName;
	}

	public void setFatherName(String fatherName) {
		this.fatherName = fatherName;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Long getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(Long mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getFileName3() {
		return fileName3;
	}

	public void setFileName3(String fileName3) {
		this.fileName3 = fileName3;
	}

	public String getFileType3() {
		return fileType3;
	}

	public void setFileType3(String fileType3) {
		this.fileType3 = fileType3;
	}

	public byte[] getData3() {
		return data3;
	}

	public void setData3(byte[] data3) {
		this.data3 = data3;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName1() {
		return fileName1;
	}

	public void setFileName1(String fileName1) {
		this.fileName1 = fileName1;
	}

	public String getFileName2() {
		return fileName2;
	}

	public void setFileName2(String fileName2) {
		this.fileName2 = fileName2;
	}

	public byte[] getData1() {
		return data1;
	}

	public void setData1(byte[] data1) {
		this.data1 = data1;
	}

	public byte[] getData2() {
		return data2;
	}

	public void setData2(byte[] data2) {
		this.data2 = data2;
	}

	public String getFileType1() {
		return fileType1;
	}

	public void setFileType1(String fileType1) {
		this.fileType1 = fileType1;
	}

	public String getFileType2() {
		return fileType2;
	}

	public void setFileType2(String fileType2) {
		this.fileType2 = fileType2;
	}

	public Users() {
	}
}
