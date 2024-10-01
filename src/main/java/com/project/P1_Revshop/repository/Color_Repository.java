package com.project.P1_Revshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.P1_Revshop.model.Color;

import io.lettuce.core.dynamic.annotation.Param;


@Repository
public interface  Color_Repository extends JpaRepository< Color,Long> {

	@Modifying
    @Query(value = "DELETE FROM color WHERE product_id = :productId", nativeQuery = true)
    void deleteColorsByProductId(@Param("productId") Long productId);
}