package com.bindeshwar.bindeshwarmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bindeshwar.bindeshwarmart.beans.ProductPackaging;

@Repository
public interface ProductPackagingRepository extends JpaRepository<ProductPackaging, Long> {

}
