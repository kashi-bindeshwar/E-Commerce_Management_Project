package com.bindeshwar.bindeshwarmart.ecommerce.beans;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import com.bindeshwar.bindeshwarmart.beans.PayementMode;
import com.bindeshwar.bindeshwarmart.beans.Users;
import com.bindeshwar.bindeshwarmart.enums.PaymentStatus;

@Entity
public class Orders {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users user;

	private String address;

	private String city;

	private String state;

	private String country;

	private String pincode;

	private String contact;

	private double total;

	private String status;

	@CreatedDate
	private LocalDate date;

	@OneToMany(mappedBy = "orders", targetEntity = OrderItems.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private List<OrderItems> orders;

	// Payment-related fields

	@ManyToOne
	@JoinColumn(name = "payment_mode_id")
	private PayementMode paymentMode; // COD / ONLINE

// SUCCESS / FAILED / PENDING

	private String transactionId; // From Razorpay (ex: pay_xxxxxx)

	private String orderReferenceId; // Razorpay Order ID (ex: order_xxxxxx)

	private String paymentGateway; // Razorpay, Paytm, Stripe, etc.

	@CreationTimestamp
	private LocalDateTime paymentDateTime; // Timestamp (or use LocalDateTime)

	private String currency; // INR / USD

	private String failureReason; // If payment failed, capture reason

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public PayementMode getPaymentMode() {
		return paymentMode;
	}

	public void setPaymentMode(PayementMode paymentMode) {
		this.paymentMode = paymentMode;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}

	public String getOrderReferenceId() {
		return orderReferenceId;
	}

	public void setOrderReferenceId(String orderReferenceId) {
		this.orderReferenceId = orderReferenceId;
	}

	public String getPaymentGateway() {
		return paymentGateway;
	}

	public void setPaymentGateway(String paymentGateway) {
		this.paymentGateway = paymentGateway;
	}

	public LocalDateTime getPaymentDateTime() {
		return paymentDateTime;
	}

	public void setPaymentDateTime(LocalDateTime paymentDateTime) {
		this.paymentDateTime = paymentDateTime;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public String getFailureReason() {
		return failureReason;
	}

	public void setFailureReason(String failureReason) {
		this.failureReason = failureReason;
	}

	public List<OrderItems> getOrders() {
		return orders;
	}

	public void setOrders(List<OrderItems> orders) {
		this.orders = orders;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Users getUser() {
		return user;
	}

	public void setUser(Users user) {
		this.user = user;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
