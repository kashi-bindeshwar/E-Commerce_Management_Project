package com.bindeshwar.bindeshwarmart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bindeshwar.bindeshwarmart.beans.ProductBrand;
import com.bindeshwar.bindeshwarmart.repository.ProductBrandRepository;
@Service
public class ProductBrandService {
	@Autowired
	ProductBrandRepository repository;
	
	public void saveProductBrand(ProductBrand prodbrand) 
	{
		repository.save(prodbrand);
	}
	
	public List<ProductBrand> getAllProductBrand() {
		return repository.findAll();
	}
	
	public void deleteProdBrand(Long id) {
		repository.deleteById(id);
	}
}
