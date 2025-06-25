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
public class WishList {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	 @OneToOne
	 @JoinColumn(name = "user_id")
	 private Users user;
	 
	 @OneToMany(mappedBy = "wishList",targetEntity = WishListItems.class, cascade = CascadeType.ALL)
	 private List<WishListItems> wishListItems;

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

	public List<WishListItems> getWishListItems() {
		return wishListItems;
	}

	public void setWishListItems(List<WishListItems> wishListItems) {
		this.wishListItems = wishListItems;
	}
	 
	 
	 
	 
}
