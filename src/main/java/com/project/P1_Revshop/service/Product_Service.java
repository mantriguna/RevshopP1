package com.project.P1_Revshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.P1_Revshop.model.Product;
import com.project.P1_Revshop.repository.Product_Repository;

@Service
public class Product_Service {
	@Autowired
    private Product_Repository productRepository;

    public void addProduct(Product product) {
        productRepository.save(product);
    }
}
