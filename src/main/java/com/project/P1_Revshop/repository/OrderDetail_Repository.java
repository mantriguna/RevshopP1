package com.project.P1_Revshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.P1_Revshop.model.Order_Detail;


@Repository
public interface OrderDetail_Repository extends JpaRepository<Order_Detail,Long> {
	
	
    @Query("SELECT od FROM Order_Detail od WHERE od.order.customer.customerId = :customerId ORDER BY od.order_detail_id DESC")
    List<Order_Detail> findByCustomerIdOrderByTimestampDesc(Long customerId);
    
    @Query("SELECT od FROM Order_Detail od WHERE od.seller.sellerId = :sellerId ORDER BY od.order_detail_id DESC")
    List<Order_Detail> findBySellerIdOrderByOrderDetailIdDesc(Long sellerId);

	
}