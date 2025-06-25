package com.bindeshwar.bindeshwarmart.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bindeshwar.bindeshwarmart.beans.ProductImages;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImages, Long> {
	Optional<ProductImages> findByProductdoc_IdAndDocName(Long productId, String docName);

	List<ProductImages> findAllByProductdoc_Id(Long productId);

}
