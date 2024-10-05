package com.project.P1_Revshop.repository;

import com.project.P1_Revshop.model.Cart;
import com.project.P1_Revshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface Cart_Repository extends JpaRepository<Cart, Long> {
    
    Optional<Cart> findByProduct(Product product);

	Optional<Cart> findByProductId(Product product);
}
