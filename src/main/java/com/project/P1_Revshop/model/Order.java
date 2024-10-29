package com.project.P1_Revshop.model;

import java.sql.Timestamp;
import jakarta.persistence.*;

@Entity
@Table(name = "orders") // Specify the table name
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long transaction_id; // Primary key

    @ManyToOne(cascade = CascadeType.ALL) // Specify the relationship
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer; // Foreign key referencing Customer

    @Column(name = "total_amount", nullable = false)
    private double total_amount;

    @Column(name = "delivery_address", nullable = false)
    private String delivery_address;

    @Column(name = "order_date", nullable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp order_date;

    // Default constructor
    public Order() {}

    // Parameterized constructor
    public Order(Customer customer, double total_amount, String delivery_address) {
        this.customer = customer;
        this.total_amount = total_amount;
        this.delivery_address = delivery_address;
        this.order_date = new Timestamp(System.currentTimeMillis()); // Set current timestamp
    }

    // Getters and Setters
    public Long getTransaction_id() {
        return transaction_id;
    }

    public void setTransaction_id(Long transaction_id) {
        this.transaction_id = transaction_id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public String getDelivery_address() {
        return delivery_address;
    }

    public void setDelivery_address(String delivery_address) {
        this.delivery_address = delivery_address;
    }

    public Timestamp getOrder_date() {
        return order_date;
    }

    public void setOrder_date(Timestamp order_date) {
        this.order_date = order_date;
    }
}
