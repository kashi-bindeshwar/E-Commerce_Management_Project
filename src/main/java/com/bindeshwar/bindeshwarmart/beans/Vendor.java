package com.bindeshwar.bindeshwarmart.beans;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Vendor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "vendorType_id")
	private VendorType vendorType;

	@ManyToMany
	@JoinTable(name = "vendor_productcategory", joinColumns = @JoinColumn(name = "vendor_id"), inverseJoinColumns = @JoinColumn(name = "productcategory_id"))
	private Set<ProductCategories> productCategories = new HashSet<>();

	@OneToMany(mappedBy = "vendor", targetEntity = Purchase.class, cascade = CascadeType.ALL)
	private List<Purchase> vendor_list;

	// Company Details
	private String compRegDate;
	private String compContact;
	private String compWebsite;
	private String compGSTIN_No;
	private String compRegistrationNo;
	private String compName;
	private String compPhoneNo;
	private String compEmail;
	private String compPanNo;
	private String vendorAlias;

	// Company Address Details
	private String address1;
	private String country;
	private String district;
	private String pincode;
	private String state;
	private String city;

	// Authorized person's details
	private String FirstName;
	private String MiddleName;
	private String LastName;
	private String email;
	private String contact;
	private String PAN;
	private String aADHARNO;
	private String Gender;

	// Bank details
	private String bankName;
	private String accountNo;
	private String iFSCCode;
	private String branchAddress;

	@OneToMany(mappedBy = "vendordoc", targetEntity = VendorDocuments.class, cascade = CascadeType.ALL)
	private List<VendorDocuments> doc_list;

	public Set<ProductCategories> getProductCategories() {
		return productCategories;
	}

	public void setProductCategories(Set<ProductCategories> productCategories) {
		this.productCategories = productCategories;
	}

	public List<Purchase> getVendor_list() {
		return vendor_list;
	}

	public void setVendor_list(List<Purchase> vendor_list) {
		this.vendor_list = vendor_list;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public VendorType getVendorType() {
		return vendorType;
	}

	public void setVendorType(VendorType vendorType) {
		this.vendorType = vendorType;
	}

	public String getCompRegDate() {
		return compRegDate;
	}

	public void setCompRegDate(String compRegDate) {
		this.compRegDate = compRegDate;
	}

	public String getCompContact() {
		return compContact;
	}

	public void setCompContact(String compContact) {
		this.compContact = compContact;
	}

	public String getCompWebsite() {
		return compWebsite;
	}

	public void setCompWebsite(String compWebsite) {
		this.compWebsite = compWebsite;
	}

	public String getCompGSTIN_No() {
		return compGSTIN_No;
	}

	public void setCompGSTIN_No(String compGSTIN_No) {
		this.compGSTIN_No = compGSTIN_No;
	}

	public String getCompRegistrationNo() {
		return compRegistrationNo;
	}

	public void setCompRegistrationNo(String compRegistrationNo) {
		this.compRegistrationNo = compRegistrationNo;
	}

	public String getCompName() {
		return compName;
	}

	public void setCompName(String compName) {
		this.compName = compName;
	}

	public String getCompPhoneNo() {
		return compPhoneNo;
	}

	public void setCompPhoneNo(String compPhoneNo) {
		this.compPhoneNo = compPhoneNo;
	}

	public String getCompEmail() {
		return compEmail;
	}

	public void setCompEmail(String compEmail) {
		this.compEmail = compEmail;
	}

	public String getCompPanNo() {
		return compPanNo;
	}

	public void setCompPanNo(String compPanNo) {
		this.compPanNo = compPanNo;
	}

	public String getVendorAlias() {
		return vendorAlias;
	}

	public void setVendorAlias(String vendorAlias) {
		this.vendorAlias = vendorAlias;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFirstName() {
		return FirstName;
	}

	public void setFirstName(String firstName) {
		FirstName = firstName;
	}

	public String getMiddleName() {
		return MiddleName;
	}

	public void setMiddleName(String middleName) {
		MiddleName = middleName;
	}

	public String getLastName() {
		return LastName;
	}

	public void setLastName(String lastName) {
		LastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getPAN() {
		return PAN;
	}

	public void setPAN(String pAN) {
		PAN = pAN;
	}

	public String getaADHARNO() {
		return aADHARNO;
	}

	public void setaADHARNO(String aADHARNO) {
		this.aADHARNO = aADHARNO;
	}

	public String getGender() {
		return Gender;
	}

	public void setGender(String gender) {
		Gender = gender;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getiFSCCode() {
		return iFSCCode;
	}

	public void setiFSCCode(String iFSCCode) {
		this.iFSCCode = iFSCCode;
	}

	public String getBranchAddress() {
		return branchAddress;
	}

	public void setBranchAddress(String branchAddress) {
		this.branchAddress = branchAddress;
	}

	public List<VendorDocuments> getDoc_list() {
		return doc_list;
	}

	public void setDoc_list(List<VendorDocuments> doc_list) {
		this.doc_list = doc_list;
	}

}
