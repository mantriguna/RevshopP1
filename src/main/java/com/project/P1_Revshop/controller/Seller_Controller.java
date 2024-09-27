package com.project.P1_Revshop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.P1_Revshop.DTO.SellerDTO;
import com.project.P1_Revshop.model.Seller;
import com.project.P1_Revshop.service.Seller_Service;


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
	public String showSellerMain(Model model) {
	    SellerDTO sellerDTO = (SellerDTO) model.asMap().get("sellerdto");

	    if (sellerDTO == null) {
	        return "redirect:/Seller/login";
	    }
	    model.addAttribute("sellerdto", sellerDTO);
	   return "Seller_Main";
	}
	@PostMapping("/login")
    public String sellerLogin(@ModelAttribute("seller") Seller seller, Model model) {
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
                // Create Seller_DTO and add it to the session
            	/*
            	 * 		this.sellerId = sellerId;
		this.name = name;
		this.personalPhoneNumber = personalPhoneNumber;
		this.email = email;
		this.homeAddress = homeAddress;
		this.panNumber = panNumber;
		this.businessAddress = businessAddress;
		this.businessPhoneNumber = businessPhoneNumber;
		this.totalEarning = totalEarning;
		this.totalItemSold = totalItemSold;
		this.currentMonthEarning = currentMonthEarning;
		this.currentMonthItemSold*/
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
                return "Seller_Main";
            }
        }
    }
}
