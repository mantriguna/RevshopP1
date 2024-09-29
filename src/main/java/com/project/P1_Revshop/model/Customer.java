package com.project.P1_Revshop.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int customerId;
	private String name;
	private long phoneNumber;
	private String email;
	private String password;
	private double walletBalance;
	public double getWalletBalance() {
		return walletBalance;
	}
	public void setWalletBalance(double walletBalance) {
		this.walletBalance = walletBalance;
	}
	@Column(columnDefinition = "TEXT")
	private String address;
	public Customer(int customerId, String name, long phoneNumber, String email, String password,
			String address,double walletBalance) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
		this.address = address;
		this.walletBalance=walletBalance;
	}
	public Customer()
	{
		
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
}
