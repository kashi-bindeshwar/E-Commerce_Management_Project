package com.bindeshwar.bindeshwarmart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bindeshwar.bindeshwarmart.beans.ProductUnits;
import com.bindeshwar.bindeshwarmart.repository.ProductUnitRepository;

@Service
public class ProductUnitService {
	@Autowired
	ProductUnitRepository repository;
	
	public void saveProductUnit(ProductUnits produnit) 
	{
		repository.save(produnit);
	}
	
	public List<ProductUnits> getAllProductUnit() {
		return repository.findAll();
	}
	
	public void deleteProdUnit(Long id) {
		repository.deleteById(id);
	}
}
