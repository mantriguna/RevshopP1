package com.project.P1_Revshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.P1_Revshop.model.Seller;

@Repository
public interface Seller_Repository extends JpaRepository<Seller,Long> {
	public Seller findByEmail(String email);
}

