package com.bindeshwar.bindeshwarmart.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bindeshwar.bindeshwarmart.ecommerce.beans.OrderItems;
import com.bindeshwar.bindeshwarmart.ecommerce.beans.Orders;
import com.bindeshwar.bindeshwarmart.ecommerce.repository.OrderItemRepository;

@Service
public class OrderItemService {
	@Autowired
	OrderItemRepository repository;
	
	public void addItem(OrderItems orderItems) 
	{
		repository.save(orderItems);
	}
	
	public List<OrderItems> getTotalItems(Orders orders) {
	    return repository.findByOrdersId(orders);
	}
	
	public void deleteItem(Long id) {
		repository.deleteById(id);
	}
}
