package com.bindeshwar.bindeshwarmart.ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bindeshwar.bindeshwarmart.beans.Users;
import com.bindeshwar.bindeshwarmart.ecommerce.beans.WishList;

@Repository
public interface WishListRepository extends JpaRepository<WishList, Long> {
	@Query("SELECT u FROM WishList u WHERE u.user=?1")
    WishList findByUserId(Users userId);
}
