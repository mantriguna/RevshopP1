package com.project.P1_Revshop.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.P1_Revshop.model.Customer;
import com.project.P1_Revshop.model.Order;
import com.project.P1_Revshop.model.Product;
import com.project.P1_Revshop.model.Review;
import com.project.P1_Revshop.service.Customer_Service;
import com.project.P1_Revshop.service.Product_Service;
import com.project.P1_Revshop.service.Review_Service;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/review")
public class Review_Controller {
	
	
	@Autowired
	private Review_Service reviewService; // Service for handling reviews
	
	@Autowired
	private Product_Service productService;
	@Autowired
	private Customer_Service customerService;
	
	@PostMapping("/submit-review")
    public ResponseEntity<Map<String, Object>> submitReview(
            @RequestBody Map<String, Object> reviewData,HttpSession session) {
        Long productId = Long.valueOf(reviewData.get("productId").toString());
        Integer rating = Integer.valueOf(reviewData.get("rating").toString());
        String comment = (String) reviewData.get("comment");
        Product product = productService.findProductById(productId);
        Long customerId = (Long) session.getAttribute("customerId");
        Customer customer = customerService.getCustomerByCustomerId(customerId);
        Map<String, Object> response = new HashMap<>();
        try {
            boolean success = reviewService.submitReview(product, customer, rating, comment);
            response.put("success", success);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }




}
