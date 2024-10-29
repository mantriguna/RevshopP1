package com.project.P1_Revshop.controller;


import com.project.P1_Revshop.exceptions.ResourceNotFoundException;
import com.project.P1_Revshop.model.Cart;
import com.project.P1_Revshop.model.Color;
import com.project.P1_Revshop.service.Cart_Service;
import com.project.P1_Revshop.service.Color_Service;
import com.project.P1_Revshop.service.Customer_Service;
import com.project.P1_Revshop.service.Product_Service;
import com.project.P1_Revshop.service.Seller_Service;
import com.project.P1_Revshop.model.Customer;
import com.project.P1_Revshop.model.Product;
import com.project.P1_Revshop.model.Seller;
import com.project.P1_Revshop.model.Wishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cart")
public class Cart_Controller {

    @Autowired
    private Cart_Service cartService;
    @Autowired
    private Customer_Service customerService;
    @Autowired
    private Product_Service productService;
    @Autowired
    private Color_Service colorService;
    @Autowired
    private Seller_Service sellerService;
    
    @PostMapping("/add")
    public ResponseEntity<Boolean> addToCart(HttpSession session,
                                             @RequestParam Long productId,
                                             @RequestParam(required=false) Long colorId) {
        try {
            Long customerId = (Long) session.getAttribute("customerId");
            if (customerId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
            }

            Customer customer = customerService.getCustomerByCustomerId(customerId);
            Product product = productService.findProductById(productId);
            Color color = colorId != null ? colorService.getColorById(colorId) : null;

            // Check if the item is already in the cart
            
            boolean isInCart = cartService.isProductInCart(customer, product, color);

            if (!isInCart) {
                // Create and add the cart item if it doesn't exist
                Cart cart = new Cart();
                cart.setCustomer(customer);
                cart.setProduct(product);
                cart.setColor(color);
                cart.setQuantity(1);  // default quantity set to 1, can be updated later
                Seller seller=sellerService.getSellerbySellerIdSeller(product.getSellerId());
                cart.setSeller(seller);
                cartService.addingintoCart(cart);
            }

            //return ResponseEntity.ok(true);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }

    


 // Method to check if a product is in the cart (used by checkIfInCart())
    @GetMapping("/check")
    public ResponseEntity<Boolean> isProductInCart(HttpSession session,
                                                   @RequestParam Long productId,
                                                   @RequestParam(required = false) Long colorId) {
        try {
            Long customerId = (Long) session.getAttribute("customerId");
            if (customerId == null) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(false);
            }

            Customer customer = customerService.getCustomerByCustomerId(customerId);
            Product product = productService.findProductById(productId);
            Color color = colorId != null ? colorService.getColorById(colorId) : null;

            boolean isInCart = cartService.isProductInCart(customer, product, color);
            
            // Use body(...) to return the value directly, without casting
            if (isInCart) {
                return ResponseEntity.status(HttpStatus.OK).body(true);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(false);
        }
    }


    
    
    
    @GetMapping("/view")
    public String viewCart(HttpSession session, Model model) {
    	Long customerId = (Long) session.getAttribute("customerId");
        if (customerId != null) {
            List<Cart> cartItems = cartService.getCartItemsByCustomer(customerId);
            double totalCartValue = cartItems.stream()
                    .mapToDouble(item -> (item.getProduct().getPrice() - 
                                          (item.getProduct().getMaxDiscount() * item.getProduct().getPrice() / 100)) 
                                          * item.getQuantity())
                    .sum();
            model.addAttribute("cartItems", cartItems);
            model.addAttribute("totalCartValue", totalCartValue);
            return "Customer_CartView";
        } else {
            return "redirect:/customer/login";
        }
    }
    

    
    
    @PostMapping("/updateCartQuantity")
    public String updateCartQuantity(@RequestParam Long cartId, @RequestParam String action) {
        if ("increase".equals(action)) {
            cartService.increaseQuantity(cartId);
        } else if ("decrease".equals(action)) {
            cartService.decreaseQuantity(cartId);
        }
        return "redirect:/cart/view";
    }
 
 // CartController.java
    @DeleteMapping("/remove/{cartId}")
    public ResponseEntity<Void> removeCartItem(@PathVariable Long cartId) {
        cartService.removeCartItem(cartId);
        return ResponseEntity.ok().build();  // Returns a success status
    }
    
    
    
    @PostMapping("/moveToWishlist/{cartId}")
    public ResponseEntity<String> moveToWishlist(@PathVariable Long cartId) {
        try {
            cartService.moveToWishlist(cartId);
            return ResponseEntity.ok("Item moved to wishlist successfully.");
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cart item not found.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to move item to wishlist.");
        }
    }

}

