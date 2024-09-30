package com.project.P1_Revshop.repository;

import com.project.P1_Revshop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Product_Repository extends JpaRepository<Product, Long> {
    List<Product> findAllByCategoryCategoryId(Long categoryId); // Fetch by Category
}
