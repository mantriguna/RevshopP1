package com.project.P1_Revshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.P1_Revshop.model.Brand;

public interface Brand_Repository extends JpaRepository<Brand,Long>{
	List<Brand> findByCategory_CategoryId(Long categoryId);
}
