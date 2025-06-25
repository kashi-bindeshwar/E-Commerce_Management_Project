package com.bindeshwar.bindeshwarmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bindeshwar.bindeshwarmart.beans.PurchaseProduct;

@Repository
public interface PurchaseProductRepository extends JpaRepository<PurchaseProduct, Long>{
	
}
