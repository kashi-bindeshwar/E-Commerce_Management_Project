package com.bindeshwar.bindeshwarmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bindeshwar.bindeshwarmart.beans.VendorDocuments;

@Repository
public interface VendorDocumentRepository extends JpaRepository<VendorDocuments, Long> {

}
