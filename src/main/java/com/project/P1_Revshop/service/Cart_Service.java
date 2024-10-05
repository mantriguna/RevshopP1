package com.project.P1_Revshop.service;

import com.project.P1_Revshop.model.Cart;
import com.project.P1_Revshop.model.Product;
import com.project.P1_Revshop.repository.Cart_Repository;
import com.project.P1_Revshop.repository.Product_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Cart_Service {

    @Autowired
    private Cart_Repository cartRepository;

    @Autowired
    private Product_Repository productRepository;

    
    public List<Cart> getCurrentCart() {
        // For now, return all items in the cart. You can filter this for user/session-based logic.
        return cartRepository.findAll();
    }

    // Add a product to the cart
    public void addProductToCart(Long productId) {
        Optional<Product> productOpt = productRepository.findById(productId);
        if (productOpt.isPresent()) {
            Product product = productOpt.get();

            // Check if the product is already in the cart
            Optional<Cart> existingCartOpt = cartRepository.findByProductId(product);

            if (existingCartOpt.isPresent()) {
                Cart existingCart = existingCartOpt.get();
                // Update the quantity if the product is already in the cart
                existingCart.setQuantity(existingCart.getQuantity() + 1);
                cartRepository.save(existingCart);
            } else {
                // Otherwise, create a new cart entry for this product
                Cart newCart = new Cart(product, 1);
                cartRepository.save(newCart);
            }
        }
    }

    // Update product quantity in the cart
    public void updateCartItemQuantity(Long cartId, int quantity) {
        Optional<Cart> cartOpt = cartRepository.findById(cartId);
        if (cartOpt.isPresent()) {
            Cart cart = cartOpt.get();
            cart.setQuantity(quantity);
            cartRepository.save(cart);
        }
    }

    // Remove an item from the cart
    public void removeCartItem(Long cartId) {
        cartRepository.deleteById(cartId);
    }
}
