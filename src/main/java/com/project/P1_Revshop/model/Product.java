package com.project.P1_Revshop.model;

import java.util.List;

import jakarta.persistence.Column;

public class Product {
	private int productId;
	private int categoryId;
    private int sellerId;
	private String productName;
	private String description;
	private double price;
	private int stockQuantity;
	@Column(columnDefinition = "TEXT")
	private List<String> imageUrls;
	@Column(columnDefinition = "TEXT")
    private List<String> colors;  
	private int threshold;
	private  Double maxDiscount;
	public Product(int productId, int categoryId, int sellerId, String productName, String description, double price,
			int stockQuantity, List<String> imageUrls, List<String> colors, int threshold, Double maxDiscount) {
		super();
		this.productId = productId;
		this.categoryId = categoryId;
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
	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
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
