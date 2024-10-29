package com.project.P1_Revshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.P1_Revshop.model.Order;
import com.project.P1_Revshop.model.Order_Detail;
import com.project.P1_Revshop.repository.OrderDetail_Repository;

@Service
public class OrderDetail_Service {
	
	@Autowired
	private OrderDetail_Repository orderDetailRepository;
	
	public void addOrderDetails(Order_Detail orderDetails) {
        orderDetailRepository.save(orderDetails);
    }

	public List<Order_Detail> getOrdersByCustomerId(Long customerId) {
        return orderDetailRepository.findByCustomerIdOrderByTimestampDesc(customerId);
    }
	
	
	public List<Order_Detail> getOrdersBySellerId(Long sellerId) {
		return orderDetailRepository.findBySellerIdOrderByOrderDetailIdDesc(sellerId);
    }

    public void updateOrderStatus(Long orderId, String status) {
        Order_Detail orderDetail = orderDetailRepository.findById(orderId)
            .orElseThrow(() -> new IllegalArgumentException("Order not found"));
        orderDetail.setStatus(status);
        orderDetailRepository.save(orderDetail);
    }
}
