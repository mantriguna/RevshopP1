package com.project.P1_Revshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.P1_Revshop.model.Customer;

public interface Customer_Repository extends JpaRepository<Customer, Long> {
	Customer findByEmail(String email);
}