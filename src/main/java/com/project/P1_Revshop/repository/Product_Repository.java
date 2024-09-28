package com.project.P1_Revshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.P1_Revshop.model.Product;


@Repository
public interface  Product_Repository extends JpaRepository< Product,Long> {

}