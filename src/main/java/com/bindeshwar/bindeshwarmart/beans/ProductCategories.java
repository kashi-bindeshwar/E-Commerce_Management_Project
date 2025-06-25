package com.bindeshwar.bindeshwarmart.beans;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

@Entity
public class ProductCategories {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank(message = "Category is required")
	private String categoryName;

	@OneToMany(mappedBy = "prodcategory", targetEntity = ProductAdd.class, cascade = CascadeType.ALL)
	private List<ProductAdd> prod_list;

	@OneToMany(mappedBy = "category", fetch = FetchType.LAZY, targetEntity = ProductSubCategory.class, cascade = CascadeType.ALL)
	private List<ProductSubCategory> prodcat_list;

	// Many to Many relation with vendor
	@ManyToMany(mappedBy = "productCategories", fetch = FetchType.EAGER)
	private Set<Vendor> vendors = new HashSet<>();

	public Set<Vendor> getVendors() {
		return vendors;
	}

	public void setVendors(Set<Vendor> vendors) {
		this.vendors = vendors;
	}

	public List<ProductSubCategory> getProdcat_list() {
		return prodcat_list;
	}

	public void setProdcat_list(List<ProductSubCategory> prodcat_list) {
		this.prodcat_list = prodcat_list;
	}

	public List<ProductAdd> getProd_list() {
		return prod_list;
	}

	public void setProd_list(List<ProductAdd> prod_list) {
		this.prod_list = prod_list;
	}

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

	// override equals and hashCode based on `id`
	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (!(o instanceof ProductCategories))
			return false;
		ProductCategories that = (ProductCategories) o;
		return id != null && id.equals(that.id);
	}

	@Override
	public int hashCode() {
		return 31;
	}
}
