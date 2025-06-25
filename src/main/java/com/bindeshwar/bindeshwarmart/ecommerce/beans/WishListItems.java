package com.bindeshwar.bindeshwarmart.ecommerce.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.bindeshwar.bindeshwarmart.beans.ProductAdd;

@Entity
public class WishListItems {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="wishList_id")
	private WishList wishList;
	
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private ProductAdd product;


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public WishList getWishList() {
		return wishList;
	}


	public void setWishList(WishList wishList) {
		this.wishList = wishList;
	}


	public ProductAdd getProduct() {
		return product;
	}


	public void setProduct(ProductAdd product) {
		this.product = product;
	}
	
	
	
}
