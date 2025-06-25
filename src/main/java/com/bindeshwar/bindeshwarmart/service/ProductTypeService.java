package com.bindeshwar.bindeshwarmart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bindeshwar.bindeshwarmart.beans.ProductType;
import com.bindeshwar.bindeshwarmart.repository.ProductTypeRepository;

@Service	
public class ProductTypeService {
	
	@Autowired
	ProductTypeRepository repository;
	
	public void saveProductType(ProductType prodtype) 
	{
		repository.save(prodtype);
	}
	
	public List<ProductType> getAllProductTypes() {
		return repository.findAll();
	}
	
	public void deleteProdType(Long id) {
		repository.deleteById(id);
	}
}
