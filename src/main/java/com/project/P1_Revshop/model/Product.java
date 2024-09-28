package com.project.P1_Revshop.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @ManyToOne
    @JoinColumn(name = "categoryId", nullable = false)
    private Category category;  // Foreign key reference to Category entity

    private Long sellerId;
    @ManyToOne
    @JoinColumn(name = "brandId", nullable = false)
    private Brand brand;
    private String productName;

    @Column(columnDefinition = "TEXT")
    private String description;

    private double price;
    private int stockQuantity;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductImage> imageUrls;  // List of ProductImage objects

    @OneToMany
    @JoinColumn(name = "productId")
    private List<Color> colors;  // List of Color entities

    private int threshold;
    private Double maxDiscount;
    // Constructor
    public Product( Category category, Long sellerId, String productName, String description,
                   double price, int stockQuantity,
                   int threshold, Double maxDiscount,Brand brand) {

        this.category = category;
        this.sellerId = sellerId;
        this.productName = productName;
        this.description = description;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.threshold = threshold;
        this.maxDiscount = maxDiscount;
        this.brand=brand;
    }

    // Default constructor
    public Product() {}

    // Getters and Setters
    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public List<ProductImage> getImageUrls() {
        return imageUrls;
    }

    public void setImageUrls(List<ProductImage> imageUrls) {
        this.imageUrls = imageUrls;
    }

    public List<Color> getColors() {
        return colors;
    }

    public void setColors(List<Color> colors) {
        this.colors = colors;
    }

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    public Double getMaxDiscount() {
        return maxDiscount;
    }

    public void setMaxDiscount(Double maxDiscount) {
        this.maxDiscount = maxDiscount;
    }
    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
