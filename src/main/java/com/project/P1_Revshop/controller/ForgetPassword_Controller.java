package com.project.P1_Revshop.controller;

import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.project.P1_Revshop.model.Customer;
import com.project.P1_Revshop.service.Customer_Service;
import com.project.P1_Revshop.service.Forgot_Service;
import com.project.P1_Revshop.service.Seller_Service;


@Controller
@RequestMapping("/ecom")
public class ForgetPassword_Controller {
	private final Map<String, String> verifyCodeStorage = new ConcurrentHashMap<>();
	private String em="";
	@Autowired
	private Forgot_Service forgotService;
	@Autowired
	private Customer_Service buyerService;
	@Autowired
	private Seller_Service sellerService;
	@GetMapping("/ForgotPassword")
	public String showForgotPasswordPage(@RequestParam("userType") String userType,Model model) {
		model.addAttribute("userType", userType);
	    return "Forget_Password"; // This returns the forgot-password.html template
	}
	@PostMapping("/api/send-verification/forgot")
    @ResponseBody
    public ResponseEntity<String> sendVerificationCode(@RequestParam("email") String email, @RequestParam("userType") String userType) {
        // Generate a random 6-digit code
        String verificationCode = String.format("%06d", new Random().nextInt(999999));
        em=email;
        Customer buyer_obj=buyerService.getCustomerByEmail(em);
        if(buyer_obj==null) {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email not Found!!!!!"); 
        }
        else {
	        try {
	        	forgotService.sendVerificationEmail(email, verificationCode);
	        	em=email;
	        	verifyCodeStorage.put(email, verificationCode);
	        	return ResponseEntity.ok("Verification Code Sent Successfully");
	        } catch (Exception e) {
	        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to Send Verification Code");
	        }
        }
    }

    @PostMapping("/api/verify-code/forgot")
    @ResponseBody
    public ResponseEntity<String> verifyCode(@RequestParam("code") String code,@RequestParam("userType") String userType) {
    	String sessionCode = verifyCodeStorage.get(em);
        if (sessionCode != null && sessionCode.equals(code)) {
        	return ResponseEntity.ok("Code verified successfully.");
        } else {
        	return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid verification code.");
        }
    }

    @PostMapping("/api/reset-password/forgot")
    public String resetPassword(
            @RequestParam("new-password") String newPassword, 
            @RequestParam("confirm-password") String confirmPassword, 
            Model model,@RequestParam("userType") String userType) throws NoSuchAlgorithmException {
        if (!newPassword.equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match.");
            return "Forget_Password";  // return to the reset form
        }

        if (em == null) {
            model.addAttribute("error", "Session expired, please try again.");
            return "Forget_Password"; // return to the reset form
        }
        if(userType.equals("buyer")) {
        	buyerService.updateBuyerPassword(em,newPassword);
        	model.addAttribute("message", "Password reset successfully.");
        	return "redirect:/customer/login";
        }
        else if(userType.equals("seller")) {
        	sellerService.updateSellerPasswordforget(em, newPassword);
        	model.addAttribute("message", "Password reset successfully.");
        	return "redirect:/Seller/login";
        }
        return "";
        
         // Redirect to the login page after success
    }
    @PostMapping("/api/verify-code/register")
    @ResponseBody
    public ResponseEntity<String> verifyCode(@RequestParam("code") String code) {
        String sessionCode = verifyCodeStorage.get(em);
        if (sessionCode != null && sessionCode.equals(code)) {
            return ResponseEntity.ok("Code verified successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid verification code."); // Ensure this message is clear
        }
    }
    @PostMapping("/api/send-verification/register")
    @ResponseBody
    public ResponseEntity<String> registersendVerificationCode(@RequestParam("email") String email, @RequestParam("userType") String userType) {
        // Check if the email already exists
        Customer buyer_obj = buyerService.getCustomerByEmail(email);
        if (buyer_obj != null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already registered. Please use a different email.");
        }

        // Generate a random 6-digit code
        String verificationCode = String.format("%06d", new Random().nextInt(999999));
        em = email;

        try {
            forgotService.CustomerRegistersendVerificationEmail(email, verificationCode);
            verifyCodeStorage.put(email, verificationCode);
            return ResponseEntity.ok("Verification Code Sent Successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to Send Verification Code: " + e.getMessage());
        }
    }

}