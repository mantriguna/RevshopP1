package com.project.P1_Revshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.P1_Revshop.model.Category;
import com.project.P1_Revshop.repository.Category_Repository;

@Service
public class Category_Service {
	@Autowired
    private Category_Repository categoryRepository;

    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    public Category findCategoryById(Long categoryId) {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        return categoryOptional.orElse(null); // Handle case when category is not found
    }
}
