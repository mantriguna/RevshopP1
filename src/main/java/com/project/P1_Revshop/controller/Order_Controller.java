package com.project.P1_Revshop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.P1_Revshop.DTO.CustomerDTO;
import com.project.P1_Revshop.model.Cart;
import com.project.P1_Revshop.model.Customer;
import com.project.P1_Revshop.model.Order;
import com.project.P1_Revshop.model.Order_Detail;
import com.project.P1_Revshop.model.Review;
import com.project.P1_Revshop.service.Cart_Service;
import com.project.P1_Revshop.service.Customer_Service;
import com.project.P1_Revshop.service.OrderDetail_Service;
import com.project.P1_Revshop.service.Order_Service;
import com.project.P1_Revshop.service.Review_Service;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/order")
public class Order_Controller {
	@Autowired
    private Cart_Service cartService;
    @Autowired
    private Customer_Service customerService;
    @Autowired
    private Order_Service orderService;
    @Autowired
    private OrderDetail_Service orderdetailService;
    
    @Autowired
    private Review_Service reviewService;
    
    
	@GetMapping("/placeOrder")
    public String placeOrder(HttpSession session, Model model) {
        // Retrieve customer ID from session
        Long customerId = (Long) session.getAttribute("customerId");
        if (customerId == null) {
            model.addAttribute("customer", null);
            return "redirect:/customer/login"; // Redirect to login if not logged in
        }

        // Fetch customer details
        Customer customer = customerService.getCustomerByCustomerId(customerId);
        model.addAttribute("customer", customer);

        // Fetch cart items for the customer
        List<Cart> cartItems = cartService.getCartItemsByCustomer(customerId);
        double totalCartValue = cartItems.stream()
                .mapToDouble(item -> (item.getProduct().getPrice() - 
                                      (item.getProduct().getMaxDiscount() * item.getProduct().getPrice() / 100)) 
                                      * item.getQuantity())
                .sum();
        model.addAttribute("cartItems", cartItems);
        
        model.addAttribute("totalCartValue", totalCartValue);

        // Return the Thymeleaf template name for the order placement page
        return "Customer_OrderSuccess"; // This should match the name of your Thymeleaf template
    }
	
	
	
	@PostMapping("/placeOrder")
    public ResponseEntity<?> placeOrder(
            @RequestParam double totalCartValue,
            @RequestParam String deliveryAddress,
            HttpSession session) {
		Long customerId = (Long) session.getAttribute("customerId");
		
		Customer customer = customerService.getCustomerByCustomerId(customerId);

        if (customer == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Please log in to place an order.");
        }

        try {
            orderService.placeOrder(customer, totalCartValue, deliveryAddress);
            session.setAttribute("customer", customer);  // Update wallet balance in session
            return ResponseEntity.ok("Order placed successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error placing order: " + e.getMessage());
        }
    }
	
	@GetMapping("/view")
    public String viewOrderHistory(HttpSession session, Model model) {
		Long customerId = (Long) session.getAttribute("customerId");
		Customer customer = customerService.getCustomerByCustomerId(customerId);
        List<Order_Detail> orders = orderdetailService.getOrdersByCustomerId(customerId);
        List<Boolean> orderReviewStatuses = new ArrayList<>();
        
        for (Order_Detail order : orders) {
	        boolean reviewExists = reviewService.existsByCustomerAndProduct(customer, order.getProduct());
	        orderReviewStatuses.add(reviewExists);
	    }
        
        // Add the orders to the model
        model.addAttribute("orders", orders);
        model.addAttribute("reviews", orderReviewStatuses);
        return "Customer_OrderHistory";
    }
}
