package com.project.P1_Revshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.project.P1_Revshop.model.Brand;

import io.lettuce.core.dynamic.annotation.Param;

public interface Brand_Repository extends JpaRepository<Brand,Long>{
	List<Brand> findByCategory_CategoryId(Long categoryId);
	//List<Brand> findByCategoryId(Long categoryId);
	@Query("SELECT b FROM Brand b WHERE b.category.id = :categoryId")
    List<Brand> findBrandsByCategory(@Param("categoryId") Long categoryId);
}
