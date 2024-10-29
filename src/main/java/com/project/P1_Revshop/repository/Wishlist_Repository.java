package com.project.P1_Revshop.repository;


import com.project.P1_Revshop.model.Customer;
import com.project.P1_Revshop.model.Product;
import com.project.P1_Revshop.model.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface Wishlist_Repository extends JpaRepository<Wishlist, Long> {
	@Query("SELECT w FROM Wishlist w WHERE w.customer = :customer AND w.product = :product")
    Optional<Wishlist> findByCustomerAndProductId(@Param("customer") Customer customer, @Param("product") Product product);

    @Modifying
    @Query("DELETE FROM Wishlist w WHERE w.customer = :customer AND w.product = :product")
    void deleteByCustomerAndProductId(@Param("customer") Customer customer, @Param("product") Product product);
    
    List<Wishlist> findByCustomer(Customer customer);
}
