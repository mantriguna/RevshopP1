package com.project.P1_Revshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.project.P1_Revshop.model.ProductImage;

import io.lettuce.core.dynamic.annotation.Param;

public interface ProductImage_Repository extends JpaRepository<ProductImage, Long> {
	@Query(value = "SELECT * FROM product_image WHERE product_id = :productId", nativeQuery = true)
    List<ProductImage> findByProductId(Long productId);
	@Modifying
    @Query(value = "DELETE FROM product_image WHERE product_id = :productId", nativeQuery = true)
    void deleteImagesByProductId(@Param("productId") Long productId);
}