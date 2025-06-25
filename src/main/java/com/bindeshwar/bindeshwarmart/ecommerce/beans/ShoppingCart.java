package com.bindeshwar.bindeshwarmart.ecommerce.beans;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.bindeshwar.bindeshwarmart.beans.Users;

@Entity
public class ShoppingCart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	 @OneToOne
	 @JoinColumn(name = "user_id")
	 private Users user;

	 @OneToMany(mappedBy = "shoppingCart",targetEntity = CartItems.class, cascade = CascadeType.ALL)
	 private List<CartItems> cartItems;
	 
	 
	public List<CartItems> getCartItems() {
		return cartItems;
	}

	public void setCartItems(List<CartItems> cartItems) {
		this.cartItems = cartItems;
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
}


  


