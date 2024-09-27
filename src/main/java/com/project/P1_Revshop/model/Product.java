package com.project.P1_Revshop.model;

import java.util.List;
import jakarta.persistence.*;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int productId;

	@ManyToOne
	@JoinColumn(name = "categoryId", nullable = false)
	private Category category;  // Foreign key reference to Category entity

    private int sellerId;
	private String productName;

	@Column(columnDefinition = "TEXT")
	private String description;
	private double price;
	private int stockQuantity;

	@ElementCollection
	@Column(columnDefinition = "TEXT")
	private List<String> imageUrls;

	@ElementCollection
	@Column(columnDefinition = "TEXT")
    private List<String> colors;  

	private int threshold;
	private Double maxDiscount;

	// Constructor
	public Product(int productId, Category category, int sellerId, String productName, String description, double price,
			int stockQuantity, List<String> imageUrls, List<String> colors, int threshold, Double maxDiscount) {
		super();
		this.productId = productId;
		this.category = category;
		this.sellerId = sellerId;
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.stockQuantity = stockQuantity;
		this.imageUrls = imageUrls;
		this.colors = colors;
		this.threshold = threshold;
		this.maxDiscount = maxDiscount;
	}

	// Default constructor
	public Product() {}

	// Getters and Setters
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public Category getCategory() {
		return category;
	}
	public void setCategory(Category category) {
		this.category = category;
	}
	public int getSellerId() {
		return sellerId;
	}
	public void setSellerId(int sellerId) {
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
	public List<String> getImageUrls() {
		return imageUrls;
	}
	public void setImageUrls(List<String> imageUrls) {
		this.imageUrls = imageUrls;
	}
	public List<String> getColors() {
		return colors;
	}
	public void setColors(List<String> colors) {
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
}
