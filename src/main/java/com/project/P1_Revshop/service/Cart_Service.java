package com.project.P1_Revshop.service;


import com.project.P1_Revshop.exceptions.ResourceNotFoundException;
import com.project.P1_Revshop.model.Cart;
import com.project.P1_Revshop.model.Color;
import com.project.P1_Revshop.model.Customer;
import com.project.P1_Revshop.model.Product;
import com.project.P1_Revshop.model.Wishlist;
import com.project.P1_Revshop.repository.Cart_Repository;
import com.project.P1_Revshop.repository.Wishlist_Repository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Cart_Service {

    @Autowired
    private Cart_Repository cartRepository;
    @Autowired
    private Wishlist_Repository wishlistRepository;
    @Autowired
    private Wishlist_Service wishlistService;
    public boolean isProductInCart(Customer customer, Product product, Color color) {

        return cartRepository.findByCustomerAndProductAndColor(customer.getCustomerId(), product.getProductId(), color.getColorId()).isPresent();

    }
    public boolean isProductInCartCP(Customer customer, Product product) {
        return cartRepository.findByCustomerAndProduct(
                customer.getCustomerId(), product.getProductId()).isPresent();
    }


    
    
    public List<Cart> getCartItemsByCustomer(Long customerId) {
        return cartRepository.findByCustomerCustomerId(customerId);
    }

    public void addingintoCart(Cart cart) {
        cartRepository.save(cart);
    }

    public void updateCartQuantity(Long cartId, int quantity) {
        Cart cart = cartRepository.findById(cartId).orElse(null);
        if (cart != null) {
            cart.setQuantity(quantity);
            cartRepository.save(cart);
        }
    }

    public void removeCartItem(Long cartId) {
        cartRepository.deleteById(cartId);
    }
    public Cart getCartItem(Long cartId) {
        return cartRepository.findById(cartId).orElse(null);
    }
    
    
    
    public void increaseQuantity(Long cartId) {
        Cart cartItem = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));
        cartItem.setQuantity(cartItem.getQuantity() + 1);
        cartRepository.save(cartItem);
    }

    public void decreaseQuantity(Long cartId) {
        Cart cartItem = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));
        if (cartItem.getQuantity() > 1) {
            cartItem.setQuantity(cartItem.getQuantity() - 1);
            cartRepository.save(cartItem);
        }
    }
    
    @Transactional
    public void moveToWishlist(Long cartId) {
        Cart cartItem = cartRepository.findById(cartId)
                .orElseThrow(() -> new ResourceNotFoundException("Cart item not found"));

        // Create a new Wishlist item based on the Cart item
        

        // Save to Wishlist and remove from Cart
        if(!wishlistService.isInWishlist(cartItem.getCustomer(),cartItem.getProduct().getProductId())) {
        	Wishlist wishlistItem = new Wishlist();
            wishlistItem.setCustomer(cartItem.getCustomer());
            wishlistItem.setProduct(cartItem.getProduct());
            wishlistRepository.save(wishlistItem);
        }
        
        cartRepository.deleteById(cartId);
    }
}
