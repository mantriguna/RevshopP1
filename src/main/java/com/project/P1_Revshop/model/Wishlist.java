package com.project.P1_Revshop.model;

import jakarta.persistence.*;
import com.project.P1_Revshop.model.Customer; // Ensure you import the Customer entity

@Entity
public class Wishlist {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long wishlistId;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer; // Change from CustomerDTO to Customer
    
    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    // Constructor with parameters
    public Wishlist(Long wishlistId, Customer customer, Product product) {
        this.wishlistId = wishlistId;
        this.customer = customer;
        this.product = product;
    }

    // Default constructor
    public Wishlist() {}

    // Getters and Setters
    public Long getWishlistId() {
        return wishlistId;
    }

    public void setWishlistId(Long wishlistId) {
        this.wishlistId = wishlistId;
    }

    public Customer getCustomer() { // Update return type to Customer
        return customer;
    }

    public void setCustomer(Customer customer) { // Update parameter type to Customer
        this.customer = customer;
    }

    public Product getProduct() { // Update method name for consistency
        return product;
    }

    public void setProduct(Product product) { // Update method name for consistency
        this.product = product;
    }
}
