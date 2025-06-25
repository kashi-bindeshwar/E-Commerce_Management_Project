package com.bindeshwar.bindeshwarmart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bindeshwar.bindeshwarmart.beans.ProductSubCategory;
@Repository
public interface ProductSubCategoyRepository extends JpaRepository<ProductSubCategory, Long> {
	 List<ProductSubCategory> findByCategoryId(Long categoryId);
}
