package com.bindeshwar.bindeshwarmart.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bindeshwar.bindeshwarmart.beans.ProductAdd;
import com.bindeshwar.bindeshwarmart.ecommerce.beans.CartItems;
import com.bindeshwar.bindeshwarmart.ecommerce.beans.ShoppingCart;
import com.bindeshwar.bindeshwarmart.ecommerce.repository.CartItemRepository;

@Service
public class CartItemService {
	@Autowired
	CartItemRepository repository;
	
	public void addItem(CartItems cartItems) 
	{
		repository.save(cartItems);
	}
	
	public List<CartItems> getTotalItems(ShoppingCart shoppingCart) {
	    return repository.findByShoppingcartId(shoppingCart);
	}
	
	public void deleteItem(Long id) {
		repository.deleteById(id);
	}
	
	
	public boolean isProductAdded(ShoppingCart shoppingCartId, ProductAdd productAdd) {
		return repository.existsByShoppingCartAndProduct(shoppingCartId, productAdd);
		
	}
}
