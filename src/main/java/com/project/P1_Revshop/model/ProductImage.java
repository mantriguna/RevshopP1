package com.project.P1_Revshop.model;

import jakarta.persistence.*;

@Entity
public class ProductImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long imageId;
    @Column(columnDefinition = "TEXT")
    private String imageUrl;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;  // Reference to Color entity

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)  // Reference to Product entity
    private Product product;  // Back reference to Product entity
    public ProductImage(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    // Constructor with product reference
    public ProductImage(String imageUrl, Category category, Product product) {
        this.imageUrl = imageUrl;
        this.category = category;
        this.product = product;  // Set the product reference
    }

    // Default constructor
    public ProductImage() {}

    // Getters and Setters
    public Long getImageId() {
        return imageId;
    }

    public void setImageId(Long imageId) {
        this.imageId = imageId;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }


    public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

}
