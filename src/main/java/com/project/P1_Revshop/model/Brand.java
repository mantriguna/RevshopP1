package com.project.P1_Revshop.model;

import jakarta.persistence.*;

@Entity
public class Brand {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long brandId;

    private String brandName;

    @ManyToOne // Many brands belong to one category
    @JoinColumn(name = "category_id") // Foreign key column in the Brand table
    private Category category;

    // Constructor with parameters
    public Brand(String brandName, Category category) {
        this.brandName = brandName;
        this.category = category;
    }

    // Default constructor
    public Brand() {}

    public Long getBrandId() {
        return brandId;
    }

    public void setBrandId(Long brandId) {
        this.brandId = brandId;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
