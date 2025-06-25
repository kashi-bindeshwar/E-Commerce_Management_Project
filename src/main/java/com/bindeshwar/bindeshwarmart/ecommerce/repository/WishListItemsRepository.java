package com.bindeshwar.bindeshwarmart.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bindeshwar.bindeshwarmart.beans.ProductAdd;
import com.bindeshwar.bindeshwarmart.ecommerce.beans.WishList;
import com.bindeshwar.bindeshwarmart.ecommerce.beans.WishListItems;


@Repository
public interface WishListItemsRepository extends JpaRepository<WishListItems, Long> {
	@Query("SELECT u FROM WishListItems u WHERE u.wishList=?1")
	List<WishListItems> findByWishListId(WishList wishListId);
	
	@Query("SELECT u FROM WishListItems u WHERE u.wishList=?1 AND u.product=?2 ")
	List<WishListItems> findByWishListIdAndProduct(WishList wishListId , ProductAdd productAdd);
}
