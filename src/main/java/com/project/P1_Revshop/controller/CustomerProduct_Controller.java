package com.project.P1_Revshop.controller;

import com.project.P1_Revshop.model.Product;
import com.project.P1_Revshop.service.Product_Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class CustomerProduct_Controller {

    @Autowired
    private Product_Service productService;

    // Display all products
    @GetMapping("/products")
    public String showProducts(Model model) {
        List<Product> products = productService.getAllProducts();
        model.addAttribute("products", products);
        return "Customer_displayProduct";  // Thymeleaf view to render products
    }

    // Display products by category
    @GetMapping("/products/category")
    public String showProductsByCategory(@RequestParam("categoryId") Long categoryId, Model model) {
        List<Product> products = productService.getProductsByCategory(categoryId);
        model.addAttribute("products", products);
        return "Customer_DisplayProduct";  // Same view, but filtered by category
    }
}
