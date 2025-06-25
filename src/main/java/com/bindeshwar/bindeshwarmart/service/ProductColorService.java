package com.bindeshwar.bindeshwarmart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bindeshwar.bindeshwarmart.beans.ProductColor;
import com.bindeshwar.bindeshwarmart.repository.ProductColorRepository;
@Service
public class ProductColorService {
	@Autowired
	ProductColorRepository repository;
	
	public void saveProductBrand(ProductColor prodcolor) 
	{
		repository.save(prodcolor);
	}
	
	public List<ProductColor> getAllProductColors() {
		return repository.findAll();
	}
	
	public void deleteProdColor(Long id) {
		repository.deleteById(id);
	}
}
