package com.project.P1_Revshop.model;

import jakarta.persistence.*;

@Entity
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long colorId;
    
    private String colorName;
    @Column(columnDefinition = "TEXT")
    private String colorUrl;
    // Constructor, getters, and setters
    public Color(String colorName,String colorUrl) {
        this.colorName = colorName;
        this.colorUrl=colorUrl;
    }

    public String getColorUrl() {
		return colorUrl;
	}

	public void setColorUrl(String colorUrl) {
		this.colorUrl = colorUrl;
	}

	public Color() {}

    public Long getColorId() {
        return colorId;
    }

    public void setColorId(Long colorId) {
        this.colorId = colorId;
    }

    public String getColorName() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName = colorName;
    }
}
