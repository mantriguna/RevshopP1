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

//    @GetMapping
//    public String listCustomers(Model model) {
//        model.addAttribute("customers", customerService.getAllCustomers());
//        return "customer";
//    }

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
    
    @GetMapping("/sign")
    public String showSignUp() {
    	return "redirect:/customer/new";
    }


//    @GetMapping("/edit/{id}")
//    public String editCustomer(@PathVariable("id") int id, Model model) {
//        CustomerDTO customerDTO = customerService.getCustomerById(id);
//        if (customerDTO != null) {
//            model.addAttribute("customer", customerDTO);
//            model.addAttribute("isEdit", true);
//            return "Customer_Sign_up";
//        }
//        return "redirect:/customer";
//    }

//    @GetMapping("/delete/{id}")
//    public String deleteCustomer(@PathVariable("id") int id) {
//        customerService.deleteCustomer(id);
//        return "redirect:/customer";
//    }
}