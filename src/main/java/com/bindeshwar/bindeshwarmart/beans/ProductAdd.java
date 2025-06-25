package com.bindeshwar.bindeshwarmart.beans;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.bindeshwar.bindeshwarmart.ecommerce.beans.CartItems;
import com.bindeshwar.bindeshwarmart.ecommerce.beans.OrderItems;
import com.bindeshwar.bindeshwarmart.ecommerce.beans.WishListItems;

@Entity
public class ProductAdd {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	@JoinColumn(name="prodcategory_id")
	private ProductCategories prodcategory;
	
	private String barCodeExist;
	private String productFor;
	private String productName;
	private String hsnCode;
	private String variantName;
	private String alias;
	private String searchKeyWord;
	private String modelNo;
	
	@Lob
	private String descOfGoods;
	
	
	private String minStock;
	private String mrpPer;
	private String costPer;
	private String TaxPer;
	
	@ManyToOne
	@JoinColumn(name="packaging_id")
	private ProductPackaging packaging;
	
	@ManyToOne
	@JoinColumn(name="subcat_id")
	private ProductSubCategory subcat1;
	
	@ManyToOne
	@JoinColumn(name="productType_id")
	private ProductType productType;
	
	private String barCode;
	private String measurement;
	@ManyToOne
	@JoinColumn(name="unit_id")
	private ProductUnits prodUnit;
	
	@ManyToOne
	@JoinColumn(name="brand_id")
	private ProductBrand brand;
	
	private String sellable;
	
	@ManyToOne
	@JoinColumn(name="color_id")
	private ProductColor prodcolor;
	
	@OneToMany(mappedBy = "productdoc",targetEntity = ProductImages.class, cascade = CascadeType.ALL , fetch = FetchType.EAGER)
	private List<ProductImages> doc_list;
	
	@OneToMany(mappedBy = "product",targetEntity = PurchaseProduct.class, cascade = CascadeType.ALL)
	private List<PurchaseProduct> purchaseprod_list;
	
	 @OneToMany(mappedBy = "product",targetEntity = CartItems.class, cascade = CascadeType.ALL)
	 private List<CartItems> cartItems;
	 
	 @OneToMany(mappedBy = "product",targetEntity = WishListItems.class, cascade = CascadeType.ALL)
	 private List<WishListItems> WishListItems;
	
	 @OneToMany(mappedBy = "product",targetEntity = OrderItems.class, cascade = CascadeType.ALL)
	 private List<OrderItems> orderItem;
	 
	public List<PurchaseProduct> getPurchaseprod_list() {
		return purchaseprod_list;
	}
	public void setPurchaseprod_list(List<PurchaseProduct> purchaseprod_list) {
		this.purchaseprod_list = purchaseprod_list;
	}

	
	public List<ProductImages> getDoc_list() {
		return doc_list;
	}
	public void setDoc_list(List<ProductImages> doc_list) {
		this.doc_list = doc_list;
	}
	public ProductColor getProdcolor() {
		return prodcolor;
	}
	public void setProdcolor(ProductColor prodcolor) {
		this.prodcolor = prodcolor;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public ProductCategories getProdcategory() {
		return prodcategory;
	}
	public void setProdcategory(ProductCategories prodcategory) {
		this.prodcategory = prodcategory;
	}
	
	public ProductUnits getProdUnit() {
		return prodUnit;
	}
	
	
	
	public List<CartItems> getCartItems() {
		return cartItems;
	}
	public void setCartItems(List<CartItems> cartItems) {
		this.cartItems = cartItems;
	}
	public List<WishListItems> getWishListItems() {
		return WishListItems;
	}
	public void setWishListItems(List<WishListItems> wishListItems) {
		WishListItems = wishListItems;
	}
	public void setProdUnit(ProductUnits prodUnit) {
		this.prodUnit = prodUnit;
	}
	public String getBarCodeExist() {
		return barCodeExist;
	}
	public void setBarCodeExist(String barCodeExist) {
		this.barCodeExist = barCodeExist;
	}
	public String getProductFor() {
		return productFor;
	}
	public void setProductFor(String productFor) {
		this.productFor = productFor;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getHsnCode() {
		return hsnCode;
	}
	public void setHsnCode(String hsnCode) {
		this.hsnCode = hsnCode;
	}
	public String getVariantName() {
		return variantName;
	}
	public void setVariantName(String variantName) {
		this.variantName = variantName;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getSearchKeyWord() {
		return searchKeyWord;
	}
	public void setSearchKeyWord(String searchKeyWord) {
		this.searchKeyWord = searchKeyWord;
	}
	public String getModelNo() {
		return modelNo;
	}
	public void setModelNo(String modelNo) {
		this.modelNo = modelNo;
	}

	
	public ProductPackaging getPackaging() {
		return packaging;
	}
	public void setPackaging(ProductPackaging packaging) {
		this.packaging = packaging;
	}
	public ProductSubCategory getSubcat1() {
		return subcat1;
	}
	public void setSubcat1(ProductSubCategory subcat1) {
		this.subcat1 = subcat1;
	}

	public ProductType getProductType() {
		return productType;
	}
	public void setProductType(ProductType productType) {
		this.productType = productType;
	}
	public String getBarCode() {
		return barCode;
	}
	public void setBarCode(String barCode) {
		this.barCode = barCode;
	}
	public String getMeasurement() {
		return measurement;
	}
	public void setMeasurement(String measurement) {
		this.measurement = measurement;
	}

	public ProductBrand getBrand() {
		return brand;
	}
	public void setBrand(ProductBrand brand) {
		this.brand = brand;
	}
	public String getSellable() {
		return sellable;
	}
	public void setSellable(String sellable) {
		this.sellable = sellable;
	}
	public String getDescOfGoods() {
		return descOfGoods;
	}
	public void setDescOfGoods(String descOfGoods) {
		this.descOfGoods = descOfGoods;
	}
	public String getMinStock() {
		return minStock;
	}
	public void setMinStock(String minStock) {
		this.minStock = minStock;
	}
	public String getMrpPer() {
		return mrpPer;
	}
	public void setMrpPer(String mrpPer) {
		this.mrpPer = mrpPer;
	}
	public String getCostPer() {
		return costPer;
	}
	public void setCostPer(String costPer) {
		this.costPer = costPer;
	}
	public String getTaxPer() {
		return TaxPer;
	}
	public void setTaxPer(String taxPer) {
		TaxPer = taxPer;
	}
	
	
}
