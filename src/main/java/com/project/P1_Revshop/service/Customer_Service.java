package com.project.P1_Revshop.service;

import java.security.NoSuchAlgorithmException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.P1_Revshop.model.Customer;
import com.project.P1_Revshop.repository.Customer_Repository;

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
    
    public void updateBuyerPassword(String email, String newPassword) throws NoSuchAlgorithmException {
         Customer buyer = customerRepository.findByEmail(email);
        buyer.setPassword(newPassword);
        customerRepository.save(buyer);
    }
}

