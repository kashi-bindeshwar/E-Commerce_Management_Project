package com.bindeshwar.bindeshwarmart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bindeshwar.bindeshwarmart.beans.ProductCategories;
import com.bindeshwar.bindeshwarmart.beans.Vendor;
import com.bindeshwar.bindeshwarmart.repository.ProductCategoriesRepository;
import com.bindeshwar.bindeshwarmart.repository.VendorRepository;

@Service
public class VendorService {
	@Autowired
	VendorRepository repository;
	
	@Autowired
	ProductCategoriesRepository prodcatrepository;
	
	public void addvendorinproductCategories(Vendor vendor, List<Long> productCategoriesIds) {
	        if (vendor != null) {
	        	vendor.getProductCategories().addAll(vendor.getProductCategories());
	        	
	        	for(ProductCategories procat : vendor.getProductCategories() ) {
	        		procat.getVendors().add(vendor);
	        	}
	        	repository.save(vendor);
	        }
	}
	
	public void saveProduct(Vendor vendor) 
	{
		repository.save(vendor);
	}
	
	public List<Vendor> getAllVendors() {
		return repository.findAll();
	}
	
	public void deleteVendor(Long id) {
		repository.deleteById(id);
	}
}
