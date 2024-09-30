//package com.project.P1_Revshop.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.project.P1_Revshop.model.Product;
//import com.project.P1_Revshop.repository.Product_Repository;
//
//@Service
//public class Product_Service {
//	@Autowired
//    private Product_Repository productRepository;
//
//    public void addProduct(Product product) {
//        productRepository.save(product);
//    }
//}

package com.project.P1_Revshop.service;


import com.project.P1_Revshop.DTO.ProductDTO;
import com.project.P1_Revshop.model.Product;
import com.project.P1_Revshop.repository.Product_Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Product_Service {

    @Autowired
    private Product_Repository productRepository;

    public Product addProduct(ProductDTO productDTO) {
        // Create a new Product entity from the DTO
        Product product = new Product();
        // Map DTO properties to the entity
        product.setProductName(productDTO.getProductName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setStockQuantity(productDTO.getStockQuantity());
        product.setThreshold(productDTO.getThreshold());
        product.setMaxDiscount(productDTO.getMaxDiscount());

        // Set Category, Brand, Colors, and Images (This may involve fetching related entities)
        // This would involve fetching entities by their IDs (categoryId, brandId)

        // Save the Product
        return productRepository.save(product);
        
        
    }
    
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    // Fetch products by category
    public List<Product> getProductsByCategory(Long categoryId) {
        return productRepository.findAllByCategoryCategoryId(categoryId);
    }
}

