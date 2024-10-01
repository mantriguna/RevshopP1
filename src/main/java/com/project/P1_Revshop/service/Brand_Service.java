package com.project.P1_Revshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.P1_Revshop.model.Brand;
import com.project.P1_Revshop.repository.Brand_Repository;

@Service
public class Brand_Service {

    @Autowired
    private Brand_Repository brandRepository; // Assuming you have a repository for Brand

    public List<Brand> getAllBrands() {
        return brandRepository.findAll(); // Fetch all brands
    }

    public Brand getBrandById(Long brandId) {
        return brandRepository.findById(brandId).orElse(null); // Fetch brand by ID
    }

    public List<Brand> getBrandsByCategoryId(Long categoryId) {
        return brandRepository.findByCategory_CategoryId(categoryId);
    }
}