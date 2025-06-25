package com.bindeshwar.bindeshwarmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bindeshwar.bindeshwarmart.beans.ProductTaxPercentage;

@Repository
public interface ProductTaxPercentageRepository extends JpaRepository<ProductTaxPercentage, Long> {

}
