package com.bindeshwar.bindeshwarmart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bindeshwar.bindeshwarmart.beans.ProductCategories;
import com.bindeshwar.bindeshwarmart.repository.ProductCategoriesRepository;

@Service
public class ProductCategoryService {
	@Autowired
	ProductCategoriesRepository repository;
	
	public void saveProductCategories(ProductCategories prodCat) 
	{
		repository.save(prodCat);
	}
	
	public List<ProductCategories> getAllProductCategories() {
		return repository.findAll();
	}
	
	public void deleteProdCategory(Long id) {
		repository.deleteById(id);
	}

	public List<ProductCategories> findAllById(List<Long> selectedCategories) {
		return repository.findAllById(selectedCategories);
	}

	public ProductCategories findById(Long id) {
		return repository.findById(id).get();
	}
	
}
