package com.project.P1_Revshop.service;

import java.util.List;

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
}
