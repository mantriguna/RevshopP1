package com.project.RevshopP1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="Seller")
public class Seller {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long sellerId;
	private String name;
	private Long personalPhoneNumber;
	private String email;
	@Column(columnDefinition = "TEXT")
	private String homeAddress;
	private String password;
	private String panNumber;
	@Column(columnDefinition = "TEXT")
	private String businessAddress;
	private Long businessPhoneNumber;
	private double totalEarning;
	private int totalItemSold;
	private double currentMonthEarning;
	private int currentMonthItemSold;
	

//	public Seller(Long sellerId, String name, Long personalPhoneNumber, String email, String homeAddress,
//			String password, String panNumber, String businessAddress, Long businessPhoneNumber, double total_earning,
//			int total_item_sold, double current_month_earning, int current_month_item_sold) {
//		super();
//		this.sellerId = sellerId;
//		this.name = name;
//		this.personalPhoneNumber = personalPhoneNumber;
//		this.email = email;
//		this.homeAddress = homeAddress;
//		this.password = password;
//		this.panNumber = panNumber;
//		this.businessAddress = businessAddress;
//		this.businessPhoneNumber = businessPhoneNumber;
//		this.total_earning = total_earning;
//		this.total_item_sold = total_item_sold;
//		this.current_month_earning = current_month_earning;
//		this.current_month_item_sold = current_month_item_sold;
//	}
	public Long getSellerId() {
		return sellerId;
	}
	public void setSellerId(Long sellerId) {
		this.sellerId = sellerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getPersonalPhoneNumber() {
		return personalPhoneNumber;
	}
	public void setPersonalPhoneNumber(Long personalPhoneNumber) {
		this.personalPhoneNumber = personalPhoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPanNumber() {
		return panNumber;
	}
	public void setPanNumber(String panNumber) {
		this.panNumber = panNumber;
	}
	public String getBusinessAddress() {
		return businessAddress;
	}
	public void setBusinessAddress(String businessAddress) {
		this.businessAddress = businessAddress;
	}
	public Long getBusinessPhoneNumber() {
		return businessPhoneNumber;
	}
	public void setBusinessPhoneNumber(Long businessPhoneNumber) {
		this.businessPhoneNumber = businessPhoneNumber;
	}
	public double getTotalEarning() {
		return totalEarning;
	}
	public void setTotalEarning(double totalEarning) {
		this.totalEarning = totalEarning;
	}
	public int getTotalItemSold() {
		return totalItemSold;
	}
	public void setTotalItemSold(int totalItemSold) {
		this.totalItemSold = totalItemSold;
	}
	public double getCurrentMonthEarning() {
		return currentMonthEarning;
	}
	public void setCurrentMonthEarning(double currentMonthEarning) {
		this.currentMonthEarning = currentMonthEarning;
	}
	public int getCurrentMonthItemSold() {
		return currentMonthItemSold;
	}
	public void setCurrentMonthItemSold(int currentMonthItemSold) {
		this.currentMonthItemSold = currentMonthItemSold;
	}
	

//	@OneToMany(mappedBy = "seller",cascade=CascadeType.ALL)
//    private List<Review> reviews;
//
//
//	
//	public List<Review> getReviews() {
//		return reviews;
//	}
//	public void setReviews(List<Review> reviews) {
//		this.reviews = reviews;
//	}
	/*
	 {
	 "name:"guna",
	 "phone_number":"8790431602",
	 "email":"guna15081947@gmail.com",
	 "home_address":"srikakulam",
	 "password":"apple@123"
	 }*/


}

