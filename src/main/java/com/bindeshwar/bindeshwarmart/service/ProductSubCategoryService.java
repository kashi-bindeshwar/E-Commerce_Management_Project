package com.bindeshwar.bindeshwarmart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bindeshwar.bindeshwarmart.beans.ProductAdd;
import com.bindeshwar.bindeshwarmart.beans.ProductSubCategory;
import com.bindeshwar.bindeshwarmart.repository.ProductSubCategoyRepository;

@Service
public class ProductSubCategoryService {
	@Autowired
	ProductSubCategoyRepository repository;
	
	public void saveProductCategories(ProductSubCategory prodSubCat) 
	{
		repository.save(prodSubCat);
	}
	
	public List<ProductSubCategory> getAllProductCategories() {
		return repository.findAll();
	}
	
	public void deleteProdCategory(Long id) {
		repository.deleteById(id);
	}
	
	public List<ProductSubCategory> getProdSubCategory(Long prodcategory_id) {
        return repository.findByCategoryId(prodcategory_id);
    }
}
