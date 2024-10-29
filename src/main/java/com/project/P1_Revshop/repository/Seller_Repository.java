package com.project.P1_Revshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.P1_Revshop.model.Seller;

@Repository
public interface Seller_Repository extends JpaRepository<Seller,Long> {

	
	@Query("SELECT s FROM Seller s WHERE s.email = :email")
	Seller findByEmail(@Param("email") String email);

}

