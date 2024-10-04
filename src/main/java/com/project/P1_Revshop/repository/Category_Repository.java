package com.project.P1_Revshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.P1_Revshop.model.Category;

@Repository
public interface Category_Repository extends JpaRepository<Category,Long> {

}
