package com.project.RevshopP1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.RevshopP1.model.Seller;
import com.project.RevshopP1.repository.Seller_Repository;


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
