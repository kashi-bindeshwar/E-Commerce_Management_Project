package com.bindeshwar.bindeshwarmart.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import com.bindeshwar.bindeshwarmart.beans.ProductAdd;
import com.bindeshwar.bindeshwarmart.beans.ProductSubCategory;

@Repository
public interface ProductAddRepository extends JpaRepository<ProductAdd, Long>{
	
}
