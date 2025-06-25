package com.bindeshwar.bindeshwarmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bindeshwar.bindeshwarmart.beans.VendorType;

@Repository
public interface VendorTypeRepository extends JpaRepository<VendorType, Long> {

}
