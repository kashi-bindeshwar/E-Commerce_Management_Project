package com.bindeshwar.bindeshwarmart.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.bindeshwar.bindeshwarmart.ecommerce.beans.Orders;
import com.bindeshwar.bindeshwarmart.ecommerce.beans.ShoppingCart;
import com.bindeshwar.bindeshwarmart.ecommerce.beans.WishList;

@Entity
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String fullName;
	
	@Column(nullable=false, unique = true)
	private String username;
	
	@Column(nullable=false, unique = true)
	private String email;
	
	private String mobil_Number;
	
	private String address;
	
	@Column(nullable=false)
	private String password;
	
	@ManyToOne
	@JoinColumn(name="category_id")
	private UsersCategory usercategory;
	
	 @OneToOne(mappedBy = "user")
	 private ShoppingCart shoppingCart;
	 
	 @OneToOne(mappedBy = "user")
	 private WishList wishList;
	
	 @OneToMany(mappedBy = "user",targetEntity = Orders.class, cascade = CascadeType.ALL)
	 private List<Orders> orders;
	 
	 
	 
	 
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public List<Orders> getOrders() {
		return orders;
	}
	public void setOrders(List<Orders> orders) {
		this.orders = orders;
	}
	public ShoppingCart getShoppingCart() {
		return shoppingCart;
	}
	public void setShoppingCart(ShoppingCart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	public WishList getWishList() {
		return wishList;
	}
	public void setWishList(WishList wishList) {
		this.wishList = wishList;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMobil_Number() {
		return mobil_Number;
	}
	public void setMobil_Number(String mobil_Number) {
		this.mobil_Number = mobil_Number;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public UsersCategory getUsercategory() {
		return usercategory;
	}
	public void setUsercategory(UsersCategory usercategory) {
		this.usercategory = usercategory;
	}
	
}
