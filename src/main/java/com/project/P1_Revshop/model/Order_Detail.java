package com.project.P1_Revshop.model;

import jakarta.persistence.*;

@Entity
public class Order_Detail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private Long order_detail_id; // Primary key

    @ManyToOne(cascade = CascadeType.ALL) // Specify the relationship
    @JoinColumn(name = "order_id", nullable = false)
    private Order order; // Foreign key referencing Orders

    @ManyToOne // Specify the relationship
    @JoinColumn(name = "product_id", nullable = false)
    private Product product; // Foreign key referencing Product
    
    @ManyToOne // Specify the relationship
    @JoinColumn(name = "color_id", nullable = false)
    private Color color;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "price_per_unit", nullable = false)
    private double price_per_unit;

    @ManyToOne // Specify the relationship
    @JoinColumn(name = "seller_id", nullable = false)
    private Seller seller; // Foreign key referencing Seller

    @Column(name = "status", length = 50)
    private String status; // Order status

    // Default constructor
    public Order_Detail() {}

    // Parameterized constructor
    public Order_Detail(Order order, Product product, int quantity, double price_per_unit, Seller seller, String status,Color color) {
        this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.price_per_unit = price_per_unit;
        this.seller = seller;
        this.status = status;
        this.color=color;
    }

    public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	// Getters and Setters
    public Long getOrder_detail_id() {
        return order_detail_id;
    }

    public void setOrder_detail_id(Long order_detail_id) {
        this.order_detail_id = order_detail_id;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice_per_unit() {
        return price_per_unit;
    }

    public void setPrice_per_unit(double price_per_unit) {
        this.price_per_unit = price_per_unit;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
