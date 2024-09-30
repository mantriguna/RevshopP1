package com.project.P1_Revshop.service;

import com.project.P1_Revshop.DTO.CustomerDTO;
import com.project.P1_Revshop.model.Customer;
import com.project.P1_Revshop.repository.CustomerRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class Customer_Service {

    @Autowired
    private CustomerRepository customerRepository;
    
    public void addCustomer(Customer customer)
    {
    	customerRepository.save(customer);
    }
     

   
//    private CustomerDTO convertToDTO(Customer customer) {
//        return new CustomerDTO(customer.getCustomerId(), customer.getName(), customer.getPhoneNumber(), customer.getEmail(), customer.getAddress());
//    }
//
//  
//    private Customer convertToEntity(CustomerDTO customerDTO) {
//        return new Customer(customerDTO.getCustomerId(), customerDTO.getName(), customerDTO.getPhoneNumber(), customerDTO.getEmail(),customerDTO.getAddress(), null);
//    }
//
//   
//    public List<CustomerDTO> getAllCustomers() {
//        return customerRepository.findAll().stream()
//                .map(this::convertToDTO)
//                .collect(Collectors.toList());
//    }
//
//  
//    public void saveCustomer(Customer customer2) {
//        customerRepository.save(customer2);
//
//    }
//
//   
//    public CustomerDTO getCustomerById(int customerId) {
//        return customerRepository.findById(customerId)
//                .map(this::convertToDTO)
//                .orElse(null);
//    }
//
//   
//    public CustomerDTO updateCustomer(int customerId, CustomerDTO customerDTO) {
//        Customer customer = convertToEntity(customerDTO);
//        customer.setCustomerId(customerId); 
//        customer = customerRepository.save(customer);
//        return convertToDTO(customer);
//    }
//
//    
//    public void deleteCustomer(int customerId) {
//        customerRepository.deleteById(customerId);
//    }
}
