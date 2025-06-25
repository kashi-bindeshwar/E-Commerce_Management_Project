package com.bindeshwar.bindeshwarmart.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bindeshwar.bindeshwarmart.beans.ProductImages;
import com.bindeshwar.bindeshwarmart.repository.ProductImageRepository;

@Service
public class ProductsImageService {
	@Autowired
	ProductImageRepository repository;

	public Optional<ProductImages> GetProductImageByProductIdAndDocName(Long productId, String docName) {
		return repository.findByProductdoc_IdAndDocName(productId, docName);
	}

	public Optional<ProductImages> getFirstProductImageByProductId(Long productId) {
		List<ProductImages> productImages = repository.findAllByProductdoc_Id(productId);
		return productImages.stream().findFirst();
	}
}
