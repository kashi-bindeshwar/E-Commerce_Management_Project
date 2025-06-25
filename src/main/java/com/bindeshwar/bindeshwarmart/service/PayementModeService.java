package com.bindeshwar.bindeshwarmart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bindeshwar.bindeshwarmart.beans.PayementMode;
import com.bindeshwar.bindeshwarmart.repository.PayementModeRepository;

@Service
public class PayementModeService {
	@Autowired
	PayementModeRepository repository;

	public void saveProductPayemode(PayementMode pMode) {
		repository.save(pMode);
	}

	public List<PayementMode> getAllProductPMode() {
		return repository.findAll();
	}

	public void deleteProdPmode(Long id) {
		repository.deleteById(id);
	}

	public PayementMode findById(Long paymentModeId) {

		PayementMode one = null;
		if (paymentModeId != null) {
			one = repository.getOne(paymentModeId);
		}
		return one;

	}
}
