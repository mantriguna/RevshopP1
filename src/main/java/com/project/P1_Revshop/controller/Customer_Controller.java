package com.project.P1_Revshop.controller;

import com.project.P1_Revshop.model.Customer;
import com.project.P1_Revshop.service.Customer_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/customer")
public class Customer_Controller {

    @Autowired
    private Customer_Service customerService;

    @GetMapping("/new")
    public String newCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "Customer_Sign_up";
    }

    @PostMapping("/save")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
        customerService.addCustomer(customer);
        return "redirect:/customer/login";
    }

    @GetMapping("/login")
    public String showLoginPage() {
        return "Customer_Login";  
    }

    @PostMapping("/login") // Handles login form submission
    public String login(@RequestParam String email, 
                        @RequestParam String password, 
                        Model model) {
        
        Customer customer = customerService.authenticate(email, password);
        if (customer != null) {
            return "redirect:/customer/buyerdashboard";
        } else {
            
            model.addAttribute("error", "Invalid email or password");
            return "Customer_Login"; 
        }
    }

   
}

   
