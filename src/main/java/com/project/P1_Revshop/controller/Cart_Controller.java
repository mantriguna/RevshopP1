package com.project.P1_Revshop.controller;

import com.project.P1_Revshop.model.Cart;
import com.project.P1_Revshop.service.Cart_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
public class Cart_Controller {

    @Autowired
    private Cart_Service cartService;

    // View Cart Page
    @GetMapping
    public String viewCart(Model model) {
        List<Cart> cart = cartService.getCurrentCart();
        model.addAttribute("cart", cart);
        return "cart";  // cart.html template
    }

    // Add product to the cart
    @PostMapping("/add")
    public String addToCart(@RequestParam("productId") Long productId) {
        cartService.addProductToCart(productId);
        return "redirect:/cart";  // Redirect to view the cart page
    }

    // Update product quantity in the cart
    @PostMapping("/update")
    public String updateCartItemQuantity(@RequestParam("cartId") Long cartId,
                                         @RequestParam("quantity") int quantity) {
        cartService.updateCartItemQuantity(cartId, quantity);
        return "redirect:/cart";  // Redirect to the cart page after update
    }

    // Remove product from the cart
    @PostMapping("/remove")
    public String removeCartItem(@RequestParam("cartId") Long cartId) {
        cartService.removeCartItem(cartId);
        return "redirect:/cart";  
    }
}
