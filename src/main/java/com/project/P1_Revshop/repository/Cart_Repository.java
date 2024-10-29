package com.project.P1_Revshop.repository;

import com.project.P1_Revshop.model.Cart;
import com.project.P1_Revshop.model.Color;
import com.project.P1_Revshop.model.Customer;
import com.project.P1_Revshop.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface Cart_Repository extends JpaRepository<Cart, Long> {
    
    //Optional<Cart> findByCustomerAndProductAndColor(Customer customer, Product product, Color color);
	
	@Query(value = "SELECT * FROM cart WHERE customer_id = :customerId AND product_id = :productId AND color_id = :colorId", nativeQuery = true)
	Optional<Cart> findByCustomerAndProductAndColor(@Param("customerId") Long customerId, 
	                                                @Param("productId") Long productId, 
	                                                @Param("colorId") Long colorId);
	
	
	@Query(value = "SELECT * FROM cart WHERE customer_id = :customerId AND product_id = :productId", nativeQuery = true)
	Optional<Cart> findByCustomerAndProduct(@Param("customerId") Long customerId, 
	                                                @Param("productId") Long productId);


    @Query(value = "SELECT * FROM Cart WHERE customer_id = :customerId", nativeQuery = true)
    List<Cart> findByCustomerCustomerId(@Param("customerId") Long customerId);

}

