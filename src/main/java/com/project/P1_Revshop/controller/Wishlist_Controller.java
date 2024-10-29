package com.project.P1_Revshop.controller;


import com.project.P1_Revshop.DTO.CustomerDTO;
import com.project.P1_Revshop.model.Customer;
import com.project.P1_Revshop.model.Product;
import com.project.P1_Revshop.model.Wishlist;
import com.project.P1_Revshop.service.Customer_Service;
import com.project.P1_Revshop.service.Wishlist_Service;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/customerwishlist")
public class Wishlist_Controller {
    
    @Autowired
    private Wishlist_Service wishlistService;

    @Autowired
    private Customer_Service customerService;


    @Autowired
    private HttpSession session;


//    @PostMapping("/toggle")
//    public ResponseEntity<Boolean> toggleWishlist(@RequestParam Long productId) { 
//        Long customerId = (Long) session.getAttribute("customerId");
//        if (customerId == null) {
//            throw new IllegalArgumentException("Customer ID must not be null");
//        }
//        Customer customer = customerService.getCustomerByCustomerId(customerId);
//        boolean isInWishlist = wishlistService.toggleWishlist(customer, productId);
//        return ResponseEntity.ok(isInWishlist); // Returns true if added, false if removed
//    }
    @PostMapping("/toggle")
    @ResponseBody
    public boolean toggleWishlist(@RequestParam Long productId, HttpSession session) {
        Long customerId = (Long) session.getAttribute("customerId");
        if (customerId == null) {
            throw new IllegalArgumentException("Customer ID must not be null");
        }
        Customer customer = customerService.getCustomerByCustomerId(customerId);
        
        // Logic to add or remove product from wishlist
        boolean isInWishlist = wishlistService.toggleWishlist(customer, productId);
        
        // Return the current status
        return isInWishlist;
    }
    @GetMapping("/wishlist")
    public String viewWishlist(Model model, HttpSession session) {
        Long customerId = (Long) session.getAttribute("customerId");
        if (customerId == null) {
            return "redirect:/login"; // Redirect to login if not logged in
        }
        
        Customer customer = customerService.getCustomerByCustomerId(customerId);
        List<Wishlist> wishlistItems = wishlistService.getWishlistItemsByCustomer(customer);
        model.addAttribute("wishlistItems", wishlistItems);
        return "Customer_Wishlist"; // Return the name of the Thymeleaf template
    }
    
    
    @DeleteMapping("/remove/{wishlistId}")
    public ResponseEntity<Void> removeFromWishlist(@PathVariable Long wishlistId) {
        boolean removed = wishlistService.removeFromWishlist(wishlistId);

        if (removed) {
            return ResponseEntity.ok().build(); // Successfully removed
        } else {
            return ResponseEntity.notFound().build(); // Item not found
        }
    }

    
    @PostMapping("/moveToCart/{wishlistId}")
    public ResponseEntity<String> moveToCart(@PathVariable Long wishlistId) {
        try {
            wishlistService.moveToCart(wishlistId);
            return ResponseEntity.ok("Item moved to cart successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to move item to cart.");
        }
    }


    
}

