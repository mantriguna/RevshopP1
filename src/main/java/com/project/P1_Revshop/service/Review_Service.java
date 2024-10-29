package com.project.P1_Revshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.P1_Revshop.model.Customer;
import com.project.P1_Revshop.model.Product;
import com.project.P1_Revshop.model.Review;
import com.project.P1_Revshop.repository.Review_Repository;


@Service
public class Review_Service {
	@Autowired
	private Review_Repository review_repository;

    public Review saveReview(Review review) {
        return review_repository.save(review);
    }

    public boolean existsByCustomerAndProduct(Customer customer, Product product) {
        return review_repository.existsByCustomerAndProduct(customer, product);
    }
	
    
    public boolean submitReview(Product product, Customer customer, Integer rating, String comment) {
        Review review = new Review();
        review.setProduct(product); // Assuming Product has a getId() method
        review.setCustomer(customer); // Assuming you have a method to get the current order ID
        review.setRating(rating);
        review.setComment(comment);
        
        review_repository.save(review); // Save the review to the database
        return true; // Return true to indicate success
    }
    
    public List<Review> getReviewsByProductId(Long productId) {
        return review_repository.findByProductId(productId);
    }
}
