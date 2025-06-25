package com.bindeshwar.bindeshwarmart.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Transient;

import org.springframework.web.multipart.MultipartFile;

@Entity
public class Organisation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String organisationName;
	private String officeNo;
	private String address;
	private String pincode;
	private String contactNo;
	private String email;
	private String city;
	private String state;
	
	@Lob
    private byte[] image;
	
	@Transient
	private MultipartFile tempimage;
	
	
	public MultipartFile getTempimage() {
		return tempimage;
	}
	public void setTempimage(MultipartFile tempimage) {
		this.tempimage = tempimage;
	}
	
	
	public Organisation() {
		super();
	}
	public Organisation(Long id, String organisationName, String officeNo, String address, String pincode,
			String contactNo, String email, String city, String state, byte[] image) {
		super();
		this.id = id;
		this.organisationName = organisationName;
		this.officeNo = officeNo;
		this.address = address;
		this.pincode = pincode;
		this.contactNo = contactNo;
		this.email = email;
		this.city = city;
		this.state = state;
		this.image = image;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getOrganisationName() {
		return organisationName;
	}
	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}
	public String getOfficeNo() {
		return officeNo;
	}
	public void setOfficeNo(String officeNo) {
		this.officeNo = officeNo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPincode() {
		return pincode;
	}
	public void setPincode(String pincode) {
		this.pincode = pincode;
	}
	public String getContactNo() {
		return contactNo;
	}
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	
	
}
