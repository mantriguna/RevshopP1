package com.project.P1_Revshop.DTO;

import java.util.List;

public class ProductDTO {

    private Long productId;
    private Long categoryId;
    private Long brandId;
    private String productName;
    private String description;
    private double price;
    private int stockQuantity;
    private int threshold;
    private Double maxDiscount;
    private List<String> imageUrls;
    private List<String> colorNames;
    private List<String> colorUrls;
	public ProductDTO(Long productId, Long categoryId, Long brandId, String productName, String description,
			double price, int stockQuantity, int threshold, Double maxDiscount, List<String> imageUrls,
			List<String> colorNames, List<String> colorUrls) {
		super();
		this.productId = productId;
		this.categoryId = categoryId;
		this.brandId = brandId;
		this.productName = productName;
		this.description = description;
		this.price = price;
		this.stockQuantity = stockQuantity;
		this.threshold = threshold;
		this.maxDiscount = maxDiscount;
		this.imageUrls = imageUrls;
		this.colorNames = colorNames;
		this.colorUrls = colorUrls;
	}
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public Long getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}
	public Long getBrandId() {
		return brandId;
	}
	public void setBrandId(Long brandId) {
		this.brandId = brandId;
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
	public List<String> getImageUrls() {
		return imageUrls;
	}
	public void setImageUrls(List<String> imageUrls) {
		this.imageUrls = imageUrls;
	}
	public List<String> getColorNames() {
		return colorNames;
	}
	public void setColorNames(List<String> colorNames) {
		this.colorNames = colorNames;
	}
	public List<String> getColorUrls() {
		return colorUrls;
	}
	public void setColorUrls(List<String> colorUrls) {
		this.colorUrls = colorUrls;
	}
	
	public ProductDTO() {
		
	}
    
    

    // Getters and Setters
    // Add constructors as needed
}
