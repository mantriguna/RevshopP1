package com.project.P1_Revshop.DTO;


import java.io.Serializable;

import jakarta.persistence.Column;

public class SellerDTO implements Serializable{
	private Long sellerId;
	private String name;
	private Long personalPhoneNumber;
	private String email;
	private String homeAddress;
	private String panNumber;
	private String businessAddress;
	private Long businessPhoneNumber;
	private double totalEarning;
	private int totalItemSold;
	private double currentMonthEarning;
	private int currentMonthItemSold;
	public SellerDTO() {}
	public SellerDTO(Long sellerId, String name, Long personalPhoneNumber, String email, String homeAddress,
			String panNumber, String businessAddress, Long businessPhoneNumber, double totalEarning, int totalItemSold,
			double currentMonthEarning, int currentMonthItemSold) {
		this.sellerId = sellerId;
		this.name = name;
		this.personalPhoneNumber = personalPhoneNumber;
		this.email = email;
		this.homeAddress = homeAddress;
		this.panNumber = panNumber;
		this.businessAddress = businessAddress;
		this.businessPhoneNumber = businessPhoneNumber;
		this.totalEarning = totalEarning;
		this.totalItemSold = totalItemSold;
		this.currentMonthEarning = currentMonthEarning;
		this.currentMonthItemSold = currentMonthItemSold;
	}
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
	
	


}
