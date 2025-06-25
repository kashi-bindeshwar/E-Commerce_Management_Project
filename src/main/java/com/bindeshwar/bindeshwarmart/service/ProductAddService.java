package com.bindeshwar.bindeshwarmart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bindeshwar.bindeshwarmart.beans.ProductAdd;
import com.bindeshwar.bindeshwarmart.repository.ProductAddRepository;

@Service
public class ProductAddService {

	@Autowired
	ProductAddRepository repository;
	
	public void saveProduct(ProductAdd prodadd) 
	{
		repository.save(prodadd);
	}
	
	public List<ProductAdd> getAllProducts() {
		return repository.findAll();
	}
	
	public void deleteProd(Long id) {
		repository.deleteById(id);
	}
	
	public ProductAdd getProductById(Long productId) {
        Optional<ProductAdd> productOptional = repository.findById(productId);
        return productOptional.orElse(null);
    }
	
}
