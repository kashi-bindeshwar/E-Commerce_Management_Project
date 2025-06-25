package com.bindeshwar.bindeshwarmart.ecommerce.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.bindeshwar.bindeshwarmart.beans.ProductAdd;

@Entity
public class OrderItems {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	 @ManyToOne
	 @JoinColumn(name = "order_id")
	 private Orders orders;
	 
	 @ManyToOne
	@JoinColumn(name="product_id")
	private ProductAdd product;
	 
	 private int quantity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Orders getOrders() {
		return orders;
	}

	public void setOrders(Orders orders) {
		this.orders = orders;
	}

	public ProductAdd getProduct() {
		return product;
	}

	public void setProduct(ProductAdd product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	 
}
