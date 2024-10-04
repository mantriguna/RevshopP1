package com.project.P1_Revshop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.P1_Revshop.DTO.SellerDTO;
import com.project.P1_Revshop.exceptions.IncorrectPasswordException;
import com.project.P1_Revshop.model.Seller;
import com.project.P1_Revshop.repository.Seller_Repository;

import jakarta.transaction.Transactional;

@Service
public class Seller_Service {
    @Autowired
    private Seller_Repository seller_ep;
//    
//    @Autowired
//    private PasswordEncoder passwordEncoder;

    public void addNewSeller(Seller seller) {
        seller_ep.save(seller);
    }

    public Seller getSellerByEmail(String email) {
        return seller_ep.findByEmail(email);
    }

    public SellerDTO getSellerBySellerId(Long sellerId) {
        Optional<Seller> optionalSeller = seller_ep.findById(sellerId);

        if (optionalSeller.isPresent()) {
            Seller existingSeller = optionalSeller.get();
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
            return sellerDTO;
        } else {
            // Handle the case when the seller is not found (optionalSeller is empty)
            return null;  // or throw an exception, based on your use case
        }
    }
    
    @Transactional
    public void updateSellerDetails(SellerDTO sellerDTO, String currentPassword,String newPassword) throws Exception {
        // Find the seller by ID
        Seller seller = seller_ep.findById(sellerDTO.getSellerId())
            .orElseThrow(() -> new Exception("Seller not found."));

        // Verify current password
        if (!currentPassword.equals(seller.getPassword())) {
            throw new IncorrectPasswordException("Current password is incorrect.");
        }

        // Update seller details
        seller.setName(sellerDTO.getName());
        seller.setEmail(sellerDTO.getEmail());
        seller.setPersonalPhoneNumber(sellerDTO.getPersonalPhoneNumber());
        seller.setHomeAddress(sellerDTO.getHomeAddress());
        seller.setPanNumber(sellerDTO.getPanNumber());
        seller.setBusinessAddress(sellerDTO.getBusinessAddress());
        seller.setBusinessPhoneNumber(sellerDTO.getBusinessPhoneNumber());
        if(newPassword!=null && !newPassword.isEmpty()) {
        	seller.setPassword(newPassword);
        }
        
        // Save updated seller details
        seller_ep.save(seller);
    }


    
    public SellerDTO getSellerById(Long sellerId) {
        Seller seller = seller_ep.findById(sellerId)
            .orElseThrow(() -> new RuntimeException("Seller not found"));

        // Map Seller entity to SellerDTO (you need a mapper or conversion logic)
        return mapToSellerDTO(seller);
    }
    // Utility method to convert Seller entity to DTO
    private SellerDTO mapToSellerDTO(Seller seller) {
        SellerDTO sellerDTO = new SellerDTO();
        sellerDTO.setSellerId(seller.getSellerId());
        sellerDTO.setName(seller.getName());
        sellerDTO.setEmail(seller.getEmail());
        sellerDTO.setPersonalPhoneNumber(seller.getPersonalPhoneNumber());
        sellerDTO.setHomeAddress(seller.getHomeAddress());
        sellerDTO.setPanNumber(seller.getPanNumber());
        sellerDTO.setBusinessAddress(seller.getBusinessAddress());
        sellerDTO.setBusinessPhoneNumber(seller.getBusinessPhoneNumber());
        return sellerDTO;
    }
    
    public void updateSellerPassword(String em, String hashPassword) {
		Seller seller=seller_ep.findByEmail(em);
		seller.setPassword(hashPassword);
		seller_ep.save(seller);
	}
}
