package com.bindeshwar.bindeshwarmart.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bindeshwar.bindeshwarmart.beans.FinancialYear;
import com.bindeshwar.bindeshwarmart.repository.FinancialYearRepository;


@Service
public class FinancialYearService {
	@Autowired
	FinancialYearRepository repository;
	
	public void saveOrganisation(FinancialYear org) 
	{
		repository.save(org);
	}
	
	 public FinancialYear getLastRecord() {
	        return repository.findLastRecord();
	    }
	
	public void UpdateOrganisation(FinancialYear lastRecord) 
	{
		
		repository.save(lastRecord);
	}
	
	public Iterable<FinancialYear> getAllOrganisation() {
		return  repository.findAll();
		
	}
	
	
	public Optional<FinancialYear> getFinancialYearId(Integer id) {
		return repository.findById(id);
	}
	
}
