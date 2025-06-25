package com.bindeshwar.bindeshwarmart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bindeshwar.bindeshwarmart.beans.ProductPackaging;
import com.bindeshwar.bindeshwarmart.repository.ProductPackagingRepository;

@Service
public class ProductPackagingService {
	
	@Autowired
	ProductPackagingRepository repository;
	
	public void saveProductPackage(ProductPackaging prodpack) 
	{
		repository.save(prodpack);
	}
	
	public List<ProductPackaging> getAllProductPackages() {
		return repository.findAll();
	}
	
	public void deleteProdPackage(Long id) {
		repository.deleteById(id);
	}
}
