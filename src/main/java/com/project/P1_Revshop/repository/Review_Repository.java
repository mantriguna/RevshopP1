package com.project.P1_Revshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.project.P1_Revshop.model.Customer;
import com.project.P1_Revshop.model.Product;
import com.project.P1_Revshop.model.Review;
@Repository
public interface Review_Repository extends JpaRepository<Review,Long> {
	
	boolean existsByCustomerAndProduct(Customer customer, Product product);
	
	@Query(value = "SELECT * FROM review WHERE product_id = ?1 ORDER BY review_id DESC", nativeQuery = true)
    List<Review> findByProductId(Long productId);

}

