package com.project.P1_Revshop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.P1_Revshop.DTO.CustomerDTO;
import com.project.P1_Revshop.model.Customer;
import com.project.P1_Revshop.model.Product;
import com.project.P1_Revshop.model.Seller;
import com.project.P1_Revshop.service.Customer_Service;
import com.project.P1_Revshop.service.Product_Service;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/customer")
public class Customer_Controller {
    @Autowired
    private Customer_Service customerService;
    @Autowired
    private Product_Service productService;

    @GetMapping("/register")
    public String newCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "Customer_Sign_up";
    }

    @PostMapping("/register")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.addCustomer(customer);
        return "redirect:/customer/login";
    }


    @GetMapping("/login")
    public String showLoginPage(Model model) {
        model.addAttribute("customer", new Customer());
        return "Customer_Login";  
    }

    @PostMapping("/login")
    public String customerLogin(@ModelAttribute("customer") Customer customer, Model model, HttpSession session) {
        String email =customer.getEmail();
        String password = customer.getPassword();

        Customer existingCustomer = customerService.getCustomerByEmail(email); // Implement this method in your service

        if (existingCustomer == null) {
            model.addAttribute("error", "Email does not exist! Please check your email.");
            return "Customer_Login"; // Return to the login page with error
        } else {
            String storedPassword = existingCustomer.getPassword();
            if (!storedPassword.equals(password)) {
                model.addAttribute("error", "Wrong password!");
                return "Customer_Login"; // Return to the login page with error
            } else {
                CustomerDTO customerDTO = new CustomerDTO(
                    existingCustomer.getCustomerId().intValue(), // Assuming customerId is Long in your model
                    existingCustomer.getName(),
                    existingCustomer.getPhoneNumber(),
                    existingCustomer.getEmail(),
                    existingCustomer.getWalletBalance(),
                    existingCustomer.getAddress()
                );
                model.addAttribute("customerdto", customerDTO);
                session.setAttribute("customerId", customerDTO.getCustomerId()); // Set customerId in session
                return "redirect:/customer/main"; // Return to Customer_Main after successful login
            }
        }
    }
    @GetMapping("/main")
    public String customerMain(Model model, HttpSession session) {
    	//Long customerId = (Long) session.getAttribute("customerId");
    	List<Product> allProducts = productService.findAllProducts();
    	model.addAttribute("productList", allProducts);
    	return "Customer_Main";
    }

   
}

   
