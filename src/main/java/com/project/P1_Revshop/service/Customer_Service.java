package com.project.P1_Revshop.service;

import java.security.NoSuchAlgorithmException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.P1_Revshop.DTO.CustomerDTO;
import com.project.P1_Revshop.exceptions.IncorrectPasswordException;
import com.project.P1_Revshop.model.Customer;
import com.project.P1_Revshop.repository.Customer_Repository;

import jakarta.persistence.Column;

@Service
public class Customer_Service {

	@Autowired
    private Customer_Repository customerRepository;
    
    public void addCustomer(Customer customer)
    {
    	customerRepository.save(customer);
    }

    public Customer getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email); // Implement this query in your repository
    }
    public Customer getCustomerByCustomerId(Long id) {
        Optional<Customer> customerOpt = customerRepository.findById(id);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            return customer;
        }
        // Implement this query in your repository
        return null;
        
    }
    public void updateBuyerPassword(String email, String newPassword) throws NoSuchAlgorithmException {
         Customer buyer = customerRepository.findByEmail(email);
        buyer.setPassword(newPassword);
        customerRepository.save(buyer);
    }
    /*private Long customerId;
	private String name;
	private long phoneNumber;
	private String email;
	private String password;
	private double walletBalance;
	@Column(columnDefinition = "TEXT")
	private String address;*/
    public CustomerDTO getCustomerById(Long customerId) {
    	Optional<Customer> customerOpt = customerRepository.findById(customerId);
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            // Convert to DTO using the constructor
            return new CustomerDTO(
                customer.getCustomerId(),
                customer.getName(),
                customer.getPhoneNumber(),
                customer.getEmail(),
                customer.getWalletBalance(), // If you want to include walletBalance
                customer.getAddress()
            );
        }
        return null; // Handle accordingly if not found
   }

    public void updateCustomerDetails(CustomerDTO customerDTO, String currentPassword) throws IncorrectPasswordException {
        Long customerId = customerDTO.getCustomerId();
        Optional<Customer> customerOpt = customerRepository.findById(customerId);
        
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            // Verify the current password
            if (!customer.getPassword().equals(currentPassword)) {
                throw new IncorrectPasswordException("Current password is incorrect.");
            }

            // Update customer details
            customer.setName(customerDTO.getName());
            customer.setPhoneNumber(customerDTO.getPhoneNumber());
            customer.setEmail(customerDTO.getEmail());
            customer.setAddress(customerDTO.getAddress());
            customer.setWalletBalance(customerDTO.getWalletBalance()); // If you want to update walletBalance
            
            // Save the updated customer
            customerRepository.save(customer);
        } else {
            // Handle the case where the customer is not found
            throw new RuntimeException("Customer not found.");
        }
    }
    public void updateCustomerPassword(String email, String hashPassword) {
        Customer customer = customerRepository.findByEmail(email);
        if (customer != null) {
            customer.setPassword(hashPassword);
            customerRepository.save(customer);
        } else {
            throw new RuntimeException("Customer with email " + email + " not found.");
        }
    }
    
    public void addToWallet(Long customerId, double addAmount) {
        Customer customer = customerRepository.findById(customerId).orElseThrow(() -> new RuntimeException("Customer not found"));
        customer.setWalletBalance(customer.getWalletBalance() + addAmount); // Add the amount to the current balance
        customerRepository.save(customer); // Save the updated customer back to the database
    }
    
    
    public void updateCustomerWallet(double updatedWallet, Long customerId) {
        Optional<Customer> optionalCustomer = customerRepository.findById(customerId);
        if (optionalCustomer.isPresent()) {
            Customer customer = optionalCustomer.get();
            customer.setWalletBalance(updatedWallet);
            customerRepository.save(customer);
        } else {
            throw new RuntimeException("Customer not found with ID: " + customerId);
        }
    }

}

