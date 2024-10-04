package com.project.P1_Revshop.controller;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.P1_Revshop.DTO.SellerDTO;
import com.project.P1_Revshop.exceptions.IncorrectPasswordException;
import com.project.P1_Revshop.model.Seller;
import com.project.P1_Revshop.service.Seller_Service;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/Seller")
public class Seller_Controller {
	@Autowired
	private Seller_Service seller_service;
	@GetMapping("/register")
	public String ShowSignUpForm(Model model) {
		model.addAttribute("seller",new Seller());
		return "Seller_Sign_up";
	}

	@PostMapping("/register")
	public String addNewSeller(Model model,@ModelAttribute Seller seller){
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
                                @RequestParam(required = false) String newPassword,
                                RedirectAttributes redirectAttributes) {
        try {
            seller_service.updateSellerDetails(sellerDTO, currentPassword,newPassword);
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

}
