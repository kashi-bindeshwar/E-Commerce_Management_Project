package com.bindeshwar.bindeshwarmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bindeshwar.bindeshwarmart.beans.ProductColor;
@Repository
public interface ProductColorRepository extends JpaRepository<ProductColor, Long> {

}
