package com.project.P1_Revshop.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.P1_Revshop.DTO.SellerDTO;
import com.project.P1_Revshop.model.Seller;
import com.project.P1_Revshop.repository.Seller_Repository;

@Service
public class Seller_Service {
    @Autowired
    private Seller_Repository seller_ep;

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
}
