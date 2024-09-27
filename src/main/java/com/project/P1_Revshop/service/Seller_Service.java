package com.project.P1_Revshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}