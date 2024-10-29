package com.project.P1_Revshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.P1_Revshop.model.Order;


@Repository
public interface Order_Repository extends JpaRepository<Order,Long> {
	
}