package com.bindeshwar.bindeshwarmart.ecommerce.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.bindeshwar.bindeshwarmart.beans.Users;
import com.bindeshwar.bindeshwarmart.ecommerce.beans.Orders;

@Repository
public interface OrdersRepository extends JpaRepository<Orders, Long> {
	@Modifying
	@Query("UPDATE Orders o SET o.status = :status WHERE o.id = :orderId")
	void updateStatus(@Param("orderId") Long orderId, @Param("status") String status);

	@Query("SELECT ord FROM Orders ord WHERE ord.user = :user ORDER BY ord.date DESC")
	List<Orders> findAllByUseryu(@Param("user") Users user);

	@Query("SELECT ord FROM Orders ord ORDER BY ord.date DESC")
	List<Orders> findAllOrders();

	List<Orders> findAllByUser(Users user);
}
