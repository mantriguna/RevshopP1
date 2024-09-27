package com.project.P1_Revshop.DTO;


public class CustomerDTO {
		private int customerId;
		private String name;
		private long phoneNumber;
		private String email;
		private double walletBalance;
		private String address;
		
		public CustomerDTO(int customerId, String name, long phoneNumber, String email, double walletBalance,
				String address) {
			super();
			this.customerId = customerId;
			this.name = name;
			this.phoneNumber = phoneNumber;
			this.email = email;
			this.walletBalance = walletBalance;
			this.address = address;
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

		public double getWalletBalance() {
			return walletBalance;
		}
		public void setWalletBalance(double walletBalance) {
			this.walletBalance = walletBalance;
		}
		public String getAddress() {
			return address;
		}
		public void setAddress(String address) {
			this.address = address;
		}
}

