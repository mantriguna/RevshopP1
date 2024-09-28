package com.project.P1_Revshop.model;

import jakarta.persistence.*;

@Entity
public class Color {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long colorId;

    private String colorName;

    // Constructor, getters, and setters
    public Color(String colorName) {
        this.colorName = colorName;
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
