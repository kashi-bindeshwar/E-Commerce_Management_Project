package com.bindeshwar.bindeshwarmart.beans;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class ProductTaxPercentage {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private int percentage;
	
	@ManyToOne
	@JoinColumn(name="tax_id")
	private ProductTax productTax;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public ProductTax getProductTax() {
		return productTax;
	}

	public void setProductTax(ProductTax productTax) {
		this.productTax = productTax;
	}
	
	
}
