package com.bindeshwar.bindeshwarmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bindeshwar.bindeshwarmart.beans.ProductTax;

@Repository
public interface ProductTaxRepository extends JpaRepository<ProductTax, Long> {

}
