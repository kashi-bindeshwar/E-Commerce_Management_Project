
package com.bindeshwar.bindeshwarmart.ecommerce.service;
  
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bindeshwar.bindeshwarmart.beans.Users;
import com.bindeshwar.bindeshwarmart.ecommerce.beans.ShoppingCart;
import com.bindeshwar.bindeshwarmart.ecommerce.repository.ShoppingCartRepository;
  
@Service
public class ShoppingCartService {
	@Autowired
	ShoppingCartRepository repository;
	
	public void createUserShoppingCart(ShoppingCart shoppingCart) 
	{
		repository.save(shoppingCart);
	}
	
	//this is for getting user shopping cart
	public ShoppingCart getShoppingCartByUserId(Users userID)
	{
		return repository.findByUserId(userID);
	}
	
	public void deleteShoppingCart(Long id) {
		repository.deleteById(id);
	}
}
 