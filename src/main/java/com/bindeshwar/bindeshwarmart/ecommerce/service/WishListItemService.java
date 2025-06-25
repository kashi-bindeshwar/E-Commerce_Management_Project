package com.bindeshwar.bindeshwarmart.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bindeshwar.bindeshwarmart.beans.ProductAdd;
import com.bindeshwar.bindeshwarmart.ecommerce.beans.WishList;
import com.bindeshwar.bindeshwarmart.ecommerce.beans.WishListItems;
import com.bindeshwar.bindeshwarmart.ecommerce.repository.WishListItemsRepository;

@Service
public class WishListItemService {

	@Autowired
	WishListItemsRepository repository;
	
	public void addItem(WishListItems WishListItems) 
	{
		repository.save(WishListItems);
	}
	
	public List<WishListItems> getTotalItems(WishList wishList) {
	    return repository.findByWishListId(wishList);
	}
	
	public List<WishListItems> findByWishListIdAndProduct(WishList wishList , ProductAdd productAdd) {
	    return repository.findByWishListIdAndProduct(wishList , productAdd);
	}
	
	
	
	public void deleteItem(Long id) {
		repository.deleteById(id);
	}
	
}
