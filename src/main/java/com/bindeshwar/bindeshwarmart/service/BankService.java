package com.bindeshwar.bindeshwarmart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bindeshwar.bindeshwarmart.beans.Bank;
import com.bindeshwar.bindeshwarmart.repository.BankRepository;

@Service
public class BankService {
	@Autowired
	BankRepository repository;
	
	public void saveProductBrand(Bank bank) 
	{
		repository.save(bank);
	}
	
	public List<Bank> getAllBanks() {
		return repository.findAll();
	}
	
	public void deleteBank(Long id) {
		repository.deleteById(id);
	}
}
