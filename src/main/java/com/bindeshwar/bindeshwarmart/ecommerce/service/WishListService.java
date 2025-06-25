package com.bindeshwar.bindeshwarmart.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bindeshwar.bindeshwarmart.beans.Users;
import com.bindeshwar.bindeshwarmart.ecommerce.beans.WishList;
import com.bindeshwar.bindeshwarmart.ecommerce.repository.WishListRepository;

@Service
public class WishListService {
	@Autowired
	WishListRepository repository;
	
	public void createUserWishList(WishList wishList) 
	{
		repository.save(wishList);
	}
	
	//this is for getting user whislit cart
	public WishList getWishListByUserId(Users userID)
	{
		return repository.findByUserId(userID);
	}
	
	public void deleteWishList(Long id) {
		repository.deleteById(id);
	}
}
