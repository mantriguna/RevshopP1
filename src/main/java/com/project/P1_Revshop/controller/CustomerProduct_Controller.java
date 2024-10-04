package com.project.P1_Revshop.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.P1_Revshop.model.Product;
import com.project.P1_Revshop.service.Customer_Service;
import com.project.P1_Revshop.service.Product_Service;



@Controller
@RequestMapping("/customerproduct")
public class CustomerProduct_Controller {
	@Autowired
    private Customer_Service customerService;
    @Autowired
    private Product_Service productService;
	
	
	@GetMapping("/displayproducts/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") Long id) {
		Optional<Product> product = productService.pfindProductById(id);
        
        return product.map(ResponseEntity::ok)
                      .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
