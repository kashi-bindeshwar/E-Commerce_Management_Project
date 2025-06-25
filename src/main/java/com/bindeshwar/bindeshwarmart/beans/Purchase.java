package com.bindeshwar.bindeshwarmart.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Purchase {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String billDate;
	private String purchaseBillNo;
	private String paidAmount;
	private String pendingAmount;
	private String totalAmount;
	
	@ManyToOne
	@JoinColumn(name="Vendor_id")
	private Vendor vendor;     

	@ManyToOne
	@JoinColumn(name="payementMode_id")
	private PayementMode  payementMode; 
	
	@OneToMany(mappedBy = "purchase",targetEntity = PurchaseProduct.class, cascade = CascadeType.ALL)
	private List<PurchaseProduct> purchaseprod_list;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBillDate() {
		return billDate;
	}

	public void setBillDate(String billDate) {
		this.billDate = billDate;
	}

	public PayementMode getPayementMode() {
		return payementMode;
	}

	public void setPayementMode(PayementMode payementMode) {
		this.payementMode = payementMode;
	}

	public String getPurchaseBillNo() {
		return purchaseBillNo;
	}

	public void setPurchaseBillNo(String purchaseBillNo) {
		this.purchaseBillNo = purchaseBillNo;
	}

	public String getPaidAmount() {
		return paidAmount;
	}

	public void setPaidAmount(String paidAmount) {
		this.paidAmount = paidAmount;
	}

	public String getPendingAmount() {
		return pendingAmount;
	}

	public void setPendingAmount(String pendingAmount) {
		this.pendingAmount = pendingAmount;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Vendor getVendor() {
		return vendor;
	}

	public void setVendor(Vendor vendor) {
		this.vendor = vendor;
	}
	
}
