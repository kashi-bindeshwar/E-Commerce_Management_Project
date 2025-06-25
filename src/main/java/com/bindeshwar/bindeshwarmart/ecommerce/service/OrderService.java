package com.bindeshwar.bindeshwarmart.ecommerce.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bindeshwar.bindeshwarmart.beans.Users;
import com.bindeshwar.bindeshwarmart.ecommerce.beans.Orders;
import com.bindeshwar.bindeshwarmart.ecommerce.repository.OrdersRepository;
import com.bindeshwar.bindeshwarmart.enums.PaymentStatus;

@Service
public class OrderService {
	@Autowired
	OrdersRepository repository;
	
	@Transactional
	public void placeOrder(Orders order) {
		repository.save(order);
	}

	
	public List<Orders> getAllOrders() {
		return repository.findAll();
	}
	
	public List<Orders> getByUser(Users user) {
		return repository.findAllByUser(user);
	}
	
	@Transactional
    public void UpdateOrderStatus(Long orderId, String status) {
        Orders order = repository.findById(orderId).orElseThrow(() -> new IllegalArgumentException("Order not found"));
        order.setStatus(status);
        repository.save(order);
    }
}
