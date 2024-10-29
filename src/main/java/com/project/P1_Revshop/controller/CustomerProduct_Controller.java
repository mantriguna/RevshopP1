package com.project.P1_Revshop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.P1_Revshop.model.Customer;
import com.project.P1_Revshop.model.Product;
import com.project.P1_Revshop.model.Review;
import com.project.P1_Revshop.service.Customer_Service;
import com.project.P1_Revshop.service.Product_Service;
import com.project.P1_Revshop.service.Review_Service;
import com.project.P1_Revshop.service.Wishlist_Service;

import jakarta.servlet.http.HttpSession;



@Controller
@RequestMapping("/customerproduct")
public class CustomerProduct_Controller {
	@Autowired
    private Customer_Service customerService;
    @Autowired
    private Product_Service productService;
	@Autowired
	private Wishlist_Service wishlistService;
	@Autowired
	private Review_Service reviewService;
	
	
	@GetMapping("/displayproducts")
    public String displayProducts(Model model) {
		List<Product> productList = productService.findAllProducts(); // Assume this method retrieves all products
        model.addAttribute("productList", productList);
        return "productList"; // Return the name of the Thymeleaf template
    }

     //This method retrieves a single product by ID
//    @GetMapping("/displayproducts/{id}")
//    public String getProductById(@PathVariable("id") Long id, Model model,HttpSession session) {
//        Product product = productService.findProductById(id);
//        model.addAttribute("product", product);
//        Long customerId = (Long) session.getAttribute("customerId");
//        Customer customer = customerService.getCustomerByCustomerId(customerId);
//        boolean isInWishlist = wishlistService.isInWishlist(customer, id);
//        model.addAttribute("isInWishlist", isInWishlist);
//        return "Customer_SingleProductView"; // Return the name of the Thymeleaf template for a single product view
//    }
    
    @GetMapping("/displayproducts/{id}")
    public String getProductById(@PathVariable("id") Long id, Model model, HttpSession session) {
        Product product = productService.findProductById(id);
        model.addAttribute("product", product);

        // Get customer info
        Long customerId = (Long) session.getAttribute("customerId");
        Customer customer = customerService.getCustomerByCustomerId(customerId);
        boolean isInWishlist = wishlistService.isInWishlist(customer, id);
        model.addAttribute("isInWishlist", isInWishlist);

        // Calculate average rating and review counts
        List<Review> reviews = reviewService.getReviewsByProductId(id);
        model.addAttribute("comments", reviews);
        if (reviews.isEmpty()) {
            model.addAttribute("averageRating", 0);
            model.addAttribute("reviewCount", 0);
        } else {
            double averageRating = reviews.stream()
                                           .mapToInt(Review::getRating) // Assuming getRating returns an integer (1-5)
                                           .average()
                                           .orElse(0);
            model.addAttribute("averageRating", averageRating);
            model.addAttribute("reviewCount", reviews.size());
            
            // Count reviews per star rating
            int[] starCounts = new int[5]; // Index 0 for 1 star, index 1 for 2 stars, etc.
            for (Review review : reviews) {
                int starRating = review.getRating();
                if (starRating >= 1 && starRating <= 5) {
                    starCounts[starRating - 1]++;
                }
            }
            model.addAttribute("starCounts", starCounts);
        }
        
        return "Customer_SingleProductView"; // Return the name of the Thymeleaf template for a single product view
    }


}
