package com.project.P1_Revshop.service;


import com.project.P1_Revshop.exceptions.ResourceNotFoundException;
import com.project.P1_Revshop.model.Cart;
import com.project.P1_Revshop.model.Customer;
import com.project.P1_Revshop.model.Product;
import com.project.P1_Revshop.model.Seller;
import com.project.P1_Revshop.model.Wishlist;
import com.project.P1_Revshop.repository.Cart_Repository;
import com.project.P1_Revshop.repository.Wishlist_Repository;

import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Wishlist_Service {
    
	 @Autowired
	    private Wishlist_Repository wishlistRepository;
	 @Autowired
	 private Cart_Repository cartRepository;
	 @Autowired
	 private Product_Service productService;
	 @Lazy
	 @Autowired
	 private Cart_Service cartService;
	 @Autowired
	 private Seller_Service sellerService;
	 @Transactional
    public boolean toggleWishlist(Customer customer, Long productId) {
		 Product product=productService.findProductById(productId);
        Optional<Wishlist> wishlistItem = wishlistRepository.findByCustomerAndProductId(customer, product);
        
        if (wishlistItem.isPresent()) {
            wishlistRepository.deleteByCustomerAndProductId(customer, product);
            return false; // Item removed
        } else {
            Wishlist wishlist = new Wishlist(null, customer, product);
            wishlistRepository.save(wishlist);
            return true; // Item added
        }
    }
	 public boolean removeFromWishlist(Long wishlistId) {
	        Optional<Wishlist> wishlistItemOptional = wishlistRepository.findById(wishlistId);
	        
	        if (wishlistItemOptional.isPresent()) {
	            wishlistRepository.delete(wishlistItemOptional.get());
	            return true; // Successfully removed
	        }
	        
	        return false; // Item not found
	    }
	 public boolean isInWishlist(Customer customer, Long productId) {
		 Product product=productService.findProductById(productId);
		    return wishlistRepository.findByCustomerAndProductId(customer, product).isPresent();
		}
	 public List<Wishlist> getWishlistItemsByCustomer(Customer customer) {
		    return wishlistRepository.findByCustomer(customer);
		}
	 
	 
	 
	 @Transactional
	    public void moveToCart(Long wishlistId) {
	        Wishlist wishlistItem = wishlistRepository.findById(wishlistId)
	                .orElseThrow(() -> new ResourceNotFoundException("Wishlist item not found"));

	        // Create a new Cart item based on the Wishlist item
	        if(!cartService.isProductInCartCP(wishlistItem.getCustomer(),wishlistItem.getProduct())) {
	        	Cart cartItem = new Cart();
		        cartItem.setCustomer(wishlistItem.getCustomer());
		        cartItem.setProduct(wishlistItem.getProduct());
		       
		        cartItem.setQuantity(1);
		        Product p1=productService.findProductById(wishlistItem.getProduct().getProductId());
		        cartItem.setColor(p1.getColors().get(0));
		        Seller seller=sellerService.getSellerbySellerIdSeller(p1.getSellerId());
		        cartItem.setSeller(seller);
		        // Save to Cart and remove from Wishlist
		        cartRepository.save(cartItem);
	        }
	        wishlistRepository.deleteById(wishlistId);
	    }


}
