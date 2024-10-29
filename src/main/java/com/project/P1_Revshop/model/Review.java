package com.project.P1_Revshop.model;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Review {
	
	@Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long review_id;
	@ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;
	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;
	@Column(columnDefinition = "TEXT")
	private String comment;
	private int rating;
	
	
	public Review() {}
	public Review(Long review_id, com.project.P1_Revshop.model.Product product, Customer customer, String comment,
			int rating) {
		super();
		this.review_id = review_id;
		this.product = product;
		this.customer = customer;
		this.comment = comment;
		this.rating = rating;
	}
	public Long getReview_id() {
		return review_id;
	}
	public void setReview_id(Long review_id) {
		this.review_id = review_id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getRating() {
		return rating;
	}
	public void setRating(int rating) {
		this.rating = rating;
	}
	
	

}
