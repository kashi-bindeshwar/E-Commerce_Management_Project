package com.bindeshwar.bindeshwarmart.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.bindeshwar.bindeshwarmart.ecommerce.beans.OrderItems;
import com.bindeshwar.bindeshwarmart.ecommerce.beans.Orders;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItems, Long> {
	@Query("SELECT u FROM OrderItems u WHERE u.orders=?1")
	List<OrderItems> findByOrdersId(Orders orders);
}
