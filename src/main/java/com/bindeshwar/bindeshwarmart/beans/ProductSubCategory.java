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
import javax.validation.constraints.NotBlank;

@Entity
public class ProductSubCategory {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Category is required")
	private String categoryName;

	/* @ManyToOne(fetch = FetchType.LAZY) */
	@ManyToOne
	@JoinColumn(name = "category_id")
	private ProductCategories category;

	@OneToMany(mappedBy = "subcat1", targetEntity = ProductAdd.class, cascade = CascadeType.ALL)
	private List<ProductAdd> prod_list;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public ProductCategories getCategory() {
		return category;
	}

	public void setCategory(ProductCategories category) {
		this.category = category;
	}

	public List<ProductAdd> getProd_list() {
		return prod_list;
	}

	public void setProd_list(List<ProductAdd> prod_list) {
		this.prod_list = prod_list;
	}

}
