package com.project.RevshopP1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.RevshopP1.model.Seller;



@Repository
public interface Seller_Repository extends JpaRepository<Seller,Long> {
	public Seller findByEmail(String email);
}

