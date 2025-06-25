package com.bindeshwar.bindeshwarmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bindeshwar.bindeshwarmart.beans.ProductCategories;

@Repository
public interface ProductCategoriesRepository extends JpaRepository<ProductCategories, Long>{

}
