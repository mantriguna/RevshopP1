package com.project.P1_Revshop.controller;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.P1_Revshop.DTO.SellerDTO;
import com.project.P1_Revshop.exceptions.IncorrectPasswordException;
import com.project.P1_Revshop.model.Order;
import com.project.P1_Revshop.model.Order_Detail;
import com.project.P1_Revshop.model.Seller;
import com.project.P1_Revshop.service.Email_Service;
import com.project.P1_Revshop.service.OrderDetail_Service;
import com.project.P1_Revshop.service.Order_Service;
import com.project.P1_Revshop.service.Seller_Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/Seller")
public class Seller_Controller {
	private final Map<String, String> verifyCodeStorage = new ConcurrentHashMap<>();
	private String em="";
	@Autowired
	private Seller_Service seller_service;
	
	@Autowired
    private Email_Service emailService;
	@Autowired
	private OrderDetail_Service orderdetailService;
	
	
	@GetMapping("/register")
	public String ShowSignUpForm(Model model) {
		model.addAttribute("seller",new Seller());
		return "Seller_Sign_up";
	}

	@PostMapping("/register")
	public String addNewSeller(Model model,@ModelAttribute Seller seller){
		if (seller_service.emailExists(seller.getEmail())) {
	        model.addAttribute("errorMessage", "Email is already registered.");
	        return "Seller_Sign_up"; // Returns to the signup page with the error message
	    }
		seller.setCurrentMonthEarning(0.0);
		seller.setCurrentMonthItemSold(0);
		seller.setTotalEarning(0.0);
		seller.setTotalItemSold(0);
		seller_service.addNewSeller(seller);
//		List<Product> products= product_service.allproducts();
//		model.addAttribute("products",products);
		return "seller_Login";
	}
	
	@GetMapping("/login")
	public String ShowloginForm(Model model) {
		
		model.addAttribute("seller",new Seller());
		return "Seller_Login";
	}
	@GetMapping("/main")
	public String showSellerMain(Model model, HttpSession session) {
	    Long sellerId = (Long) session.getAttribute("sellerId");
	    //SellerDTO sellerDTO=(SellerDTO) session.getAttribute("seller");
	    if (sellerId == null) {
	        return "redirect:/Seller/login";  // Redirect to login if sellerId is not found in session
	    }

	    SellerDTO sellerDTO = seller_service.getSellerBySellerId(sellerId);

	    if (sellerDTO == null) {
	        return "redirect:/Seller/login";  // Redirect to login if no sellerDTO found
	    }

	    model.addAttribute("sellerdto", sellerDTO);

	    return "Seller_Main"; // Render the Seller_Main view with sellerDTO
	}

	@PostMapping("/login")
	public String sellerLogin(@ModelAttribute("seller") Seller seller, Model model, HttpSession session) {
	    String email = seller.getEmail();
	    String password = seller.getPassword();

	    Seller existingSeller = seller_service.getSellerByEmail(email);

	    if (existingSeller == null) {
	        model.addAttribute("error", "Email does not exist! Please check your email.");
	        return "Seller_Login"; // Return to the login page with error
	    } else {
	        String storedPassword = existingSeller.getPassword();
	        if (!storedPassword.equals(password)) {
	            model.addAttribute("error", "Wrong password!");
	            return "Seller_Login"; // Return to the login page with error
	        } else {
	            SellerDTO sellerDTO = new SellerDTO(
	                existingSeller.getSellerId(),
	                existingSeller.getName(),
	                existingSeller.getPersonalPhoneNumber(),
	                existingSeller.getEmail(),
	                existingSeller.getHomeAddress(),
	                existingSeller.getPanNumber(),
	                existingSeller.getBusinessAddress(),
	                existingSeller.getBusinessPhoneNumber(),
	                existingSeller.getTotalEarning(),
	                existingSeller.getTotalItemSold(),
	                existingSeller.getCurrentMonthEarning(),
	                existingSeller.getCurrentMonthItemSold()
	            );
	            model.addAttribute("sellerdto", sellerDTO);
	            session.setAttribute("sellerId", sellerDTO.getSellerId()); // Set sellerId in session
	            session.setAttribute("seller", sellerDTO);
	            return "Seller_Main"; // Return to Seller_Main after successful login
	        }
	    }
	}
	
	
	
	
	@GetMapping("/updateprofile")
    public String getUpdateSellerPage(HttpSession session, Model model) {
        // Fetch the seller information using the service
		Long sellerId = (Long) session.getAttribute("sellerId");
        SellerDTO sellerDTO = seller_service.getSellerById(sellerId);

        // Add the sellerDTO object to the model, which will be passed to Thymeleaf
        model.addAttribute("seller", sellerDTO);

        // Return the name of the Thymeleaf template (HTML file)
        return "Seller_Profileupdate"; // Assuming your Thymeleaf template file is named updateSellerDetails.html
    }

	@PostMapping("/update")
    public String updateSeller(@ModelAttribute SellerDTO sellerDTO,
                                @RequestParam String currentPassword,
                                RedirectAttributes redirectAttributes) {
        try {
            seller_service.updateSellerDetails(sellerDTO, currentPassword);
            // Optionally add a success message
            redirectAttributes.addFlashAttribute("successMessage", "Seller details updated successfully.");
            return "redirect:/Seller/main"; // Redirect to the seller's profile or another page
        } catch (IncorrectPasswordException e) {
            // Redirect to the login page with an error message
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/Seller/login"; // Change to your actual login page URL
        } catch (Exception e) {
            // Handle other exceptions
            redirectAttributes.addFlashAttribute("errorMessage", "An error occurred while updating details.");
            return "redirect:/Seller/main"; // Redirect to an appropriate page
        }
    }
	
	
	@GetMapping("/changepassword")
	public String SellerChangePasswordForm(HttpSession session,Model model) {
		Long sellerId = (Long) session.getAttribute("sellerId");
		 
		 if (sellerId == null) {
	            return "redirect:/login";  // Redirect to login if seller not in session
	        }

		 	SellerDTO sellerDTO = seller_service.getSellerById(sellerId);
	        String email = sellerDTO.getEmail();

	        // Generate a 6-digit OTP
	        String verificationCode = String.format("%06d", new Random().nextInt(999999));
	        em=email;
	        verifyCodeStorage.put(email, verificationCode);
	        // Store OTP for verification later
	        

	        // Send OTP to the seller's email
	        emailService.SellerPasswordChange(sellerDTO.getName(),email, verificationCode);

	        model.addAttribute("sellerEmail", email);
		return "Seller_ChangePassword";
	}
	@PostMapping("/verifyotp")
    @ResponseBody
    public ResponseEntity<String> verifyCode(@RequestParam("code") String code) {
        String sessionCode = verifyCodeStorage.get(em);
        if (sessionCode != null && sessionCode.equals(code)) {
            return ResponseEntity.ok("Code verified successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid verification code."); // Ensure this message is clear
        }
    }
	
	@PostMapping("/changepassword")
	@ResponseBody
	public ResponseEntity<String> handlePasswordChange(
	        @RequestParam("new-password") String newPassword,
	        @RequestParam("confirm-password") String confirmPassword,
	        HttpSession session) {

	    // Validate the session
	    Long sellerId = (Long) session.getAttribute("sellerId");
	    if (sellerId == null) {
	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("You need to be logged in to change your password.");
	    }
	    
	    SellerDTO sellerDTO = seller_service.getSellerById(sellerId);
	    String email=sellerDTO.getEmail();
	    // Check if passwords match
	    if (!newPassword.equals(confirmPassword)) {
	        return ResponseEntity.badRequest().body("Passwords do not match. Please try again.");
	    }

	    try {
	    

	        seller_service.updateSellerPasswordforget(sellerDTO.getEmail(), newPassword);

	        // Invalidate the session after password change
	        session.invalidate(); 

	        // Optionally, remove the used verification code from storage
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
	
	
	@GetMapping("/receivedOrders")
    public String showReceivedOrders(Model model, HttpSession session) {
        Long sellerId = (Long) session.getAttribute("sellerId");
        if (sellerId == null) {
            return "redirect:/Seller/login";
        }

        List<Order_Detail> orders = orderdetailService.getOrdersBySellerId(sellerId);
        model.addAttribute("orders", orders);
        return "Seller_ReceivedOrder";  // Thymeleaf template
    }

    @PostMapping("/receivedOrders")
    public String updateOrderStatus(@RequestParam("orderId") Long orderId,
                                    @RequestParam("status") String status,
                                    RedirectAttributes redirectAttributes) {
        try {
            orderdetailService.updateOrderStatus(orderId, status);
            redirectAttributes.addFlashAttribute("successMessage", "Order status updated successfully.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("errorMessage", "Failed to update order status.");
        }

        return "redirect:/Seller/receivedOrders";  // Redirect back to received orders page
    }
    
    @GetMapping("/CompletedOrders")
    public String showCompletedOrders(Model model, HttpSession session) {
        Long sellerId = (Long) session.getAttribute("sellerId");
        if (sellerId == null) {
            return "redirect:/Seller/login";
        }

        List<Order_Detail> orders = orderdetailService.getOrdersBySellerId(sellerId);
        model.addAttribute("orders", orders);
        return "Seller_CompletedOrder";  // Thymeleaf template
    }
    
    

}
