package com.bindeshwar.bindeshwarmart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bindeshwar.bindeshwarmart.beans.Bank;

@Repository
public interface BankRepository extends JpaRepository<Bank, Long>{

}
