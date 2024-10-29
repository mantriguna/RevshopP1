package com.project.P1_Revshop.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.P1_Revshop.DTO.CustomerDTO;
import com.project.P1_Revshop.exceptions.IncorrectPasswordException;
import com.project.P1_Revshop.model.Customer;
import com.project.P1_Revshop.model.Product;
import com.project.P1_Revshop.model.Seller;
import com.project.P1_Revshop.service.Customer_Service;
import com.project.P1_Revshop.service.Email_Service;
import com.project.P1_Revshop.service.Product_Service;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/customer")
public class Customer_Controller {
	private final Map<String, String> verifyCodeStorage = new ConcurrentHashMap<>();
	private String em="";
    @Autowired
    private Customer_Service customerService;
    @Autowired
    private Email_Service emailService;
    @Autowired
    private Product_Service productService;

    @GetMapping("/register")
    public String newCustomer(Model model) {
        model.addAttribute("customer", new Customer());
        return "Customer_Sign_up";
    }

    @PostMapping("/register")
    public String saveCustomer(@ModelAttribute("customer") Customer customer) {
    	System.out.println("==============================="+customer.getEmail()+"================================================");
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
                    existingCustomer.getCustomerId(), // Assuming customerId is Long in your model
                    existingCustomer.getName(),
                    existingCustomer.getPhoneNumber(),
                    existingCustomer.getEmail(),
                    existingCustomer.getWalletBalance(),
                    existingCustomer.getAddress()
                );
                model.addAttribute("customerdto", customerDTO);
                session.setAttribute("customerId", customerDTO.getCustomerId()); // Set customerId in session
                List<Product> allProducts = productService.findAllProducts();
                model.addAttribute("customer", customerDTO);
            	model.addAttribute("productList", allProducts);
            	return "Customer_Main"; // Return to Customer_Main after successful login
            }
        }
    }
    @GetMapping("/main")
    public String customerMain(Model model, HttpSession session) {
    	//Long customerId = (Long) session.getAttribute("customerId");
    	List<Product> allProducts = productService.findAllProducts();
    	Long customerId = (Long) session.getAttribute("customerId");
        CustomerDTO customerDTO = customerService.getCustomerById(customerId);
        model.addAttribute("customer", customerDTO);
    	model.addAttribute("productList", allProducts);
    	return "Customer_Main";
    }
    
    @GetMapping("/updateCustomerProfile")
    public String getUpdateCustomerPage(HttpSession session, Model model) {
        Long customerId = (Long) session.getAttribute("customerId");
        CustomerDTO customerDTO = customerService.getCustomerById(customerId);

        model.addAttribute("customer", customerDTO);
        return "Customer_ProfileUpdate"; // Thymeleaf template name
    }

    @PostMapping("/updateCustomer")
    public String updateCustomer(@ModelAttribute CustomerDTO customerDTO,
                                  @RequestParam String currentPassword,
                                  RedirectAttributes redirectAttributes) {
        try {
            customerService.updateCustomerDetails(customerDTO, currentPassword);
            redirectAttributes.addFlashAttribute("successMessage", "Customer details updated successfully.");
            return "redirect:/customer/main"; // Change to appropriate page
        } catch (IncorrectPasswordException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/customer/login"; // Change to your actual login page URL
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "An error occurred while updating details.");
            return "redirect:/customer/main"; // Redirect to an appropriate page
        }
    }

    
    
    @GetMapping("/changepassword")
    public String CustomerChangePasswordForm(HttpSession session, Model model) {
        Long customerId = (Long) session.getAttribute("customerId");

        if (customerId == null) {
            return "redirect:/login";  // Redirect to login if customer not in session
        }

        CustomerDTO customerDTO = customerService.getCustomerById(customerId);
        String email = customerDTO.getEmail();

        // Generate a 6-digit OTP
        String verificationCode = String.format("%06d", new Random().nextInt(999999));
        em = email;
        verifyCodeStorage.put(email, verificationCode);

        // Send OTP to the customer's email
        emailService.CustomerPasswordChange(customerDTO.getName(), email, verificationCode);

        model.addAttribute("customerEmail", email);
        return "Customer_ChangePassword";
    }

    @PostMapping("/verifyotp")
    @ResponseBody
    public ResponseEntity<String> verifyCode(@RequestParam("code") String code) {
        String sessionCode = verifyCodeStorage.get(em);
        if (sessionCode != null && sessionCode.equals(code)) {
            return ResponseEntity.ok("Code verified successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid verification code.");
        }
    }

    @PostMapping("/changepassword")
    @ResponseBody
    public ResponseEntity<String> handlePasswordChange(
            @RequestParam("new-password") String newPassword,
            @RequestParam("confirm-password") String confirmPassword,
            HttpSession session) {

        Long customerId = (Long) session.getAttribute("customerId");
        if (customerId == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You need to be logged in to change your password.");
        }

        CustomerDTO customerDTO = customerService.getCustomerById(customerId);
        String email = customerDTO.getEmail();

        if (!newPassword.equals(confirmPassword)) {
            return ResponseEntity.badRequest().body("Passwords do not match. Please try again.");
        }

        try {
            customerService.updateCustomerPassword(email, newPassword);

            session.invalidate();

            if (verifyCodeStorage != null && verifyCodeStorage.containsKey(email)) {
                verifyCodeStorage.remove(email);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Verification code not found.");
            }

            return ResponseEntity.ok("Password changed successfully!");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to change password. Please try again later.");
        }
    }
    
    
    
    @GetMapping("/displayproducts/{id}")
    public String getProductById(@PathVariable("id") Long id, Model model) {
        Optional<Product> product = productService.pfindProductById(id);
        
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "Customer_ProductView"; // Replace with the name of your Thymeleaf view
        } else {
            return "productNotFound"; // Optional: handle product not found
        }
    }

    
    
    
    @GetMapping("/wallet")
    public String viewWallet(HttpSession session, Model model) {
        Long customerId = (Long) session.getAttribute("customerId");
        
        if (customerId == null) {
            return "redirect:/customer/login";  // Redirect to login if customer not in session
        }

        CustomerDTO customerDTO = customerService.getCustomerById(customerId); // Retrieve customer details including wallet balance
        model.addAttribute("customer", customerDTO);  // Add customer data to the model

        return "Customer_Wallet";  // Return the wallet view page
    }
    
    @PostMapping("/addToWallet")
    public String addToWallet(@RequestParam("addAmount") double addAmount, HttpSession session, RedirectAttributes redirectAttributes) {
        Long customerId = (Long) session.getAttribute("customerId");
        if (customerId != null) {
            customerService.addToWallet(customerId, addAmount); // Implement this in your Customer_Service
            redirectAttributes.addFlashAttribute("message", "Amount added successfully!");
        }
        return "redirect:/customer/wallet"; // Redirect back to wallet page
    }

   
}

   
