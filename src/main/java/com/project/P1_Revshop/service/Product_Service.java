package com.project.P1_Revshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.P1_Revshop.exceptions.ResourceNotFoundException;
import com.project.P1_Revshop.model.Brand;
import com.project.P1_Revshop.model.Category;
import com.project.P1_Revshop.model.Color;
import com.project.P1_Revshop.model.Product;
import com.project.P1_Revshop.model.ProductImage;
import com.project.P1_Revshop.repository.Brand_Repository;
import com.project.P1_Revshop.repository.Category_Repository;
import com.project.P1_Revshop.repository.Color_Repository;
import com.project.P1_Revshop.repository.ProductImage_Repository;
import com.project.P1_Revshop.repository.Product_Repository;

import jakarta.transaction.Transactional;

@Service
public class Product_Service {
	
    @Autowired
    private Product_Repository productRepository;
    
    @Autowired
    private ProductImage_Repository productImageRepository;
    
    @Autowired
    private Color_Repository colorRepository;
    
    public List<Product> findAllProducts() {
        return productRepository.findAll();
    }
//    public List<Product> findByCategoryId(Long categoryId) {
//        return productRepository.findByCategoryId(categoryId);
//    }
   
    public void addProduct(Product product) {
        productRepository.save(product);
    }
    
    public Product findProductById(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        return productOptional.orElse(null); // Handle case when product is not found
    }
    public Optional<Product> pfindProductById(Long productId) {
        Optional<Product> productOptional = productRepository.findById(productId);
        return productOptional; // Handle case when product is not found
    }

    
    public List<Product> findProductsBySellerId(Long sellerId) {
        List<Product> products = productRepository.findAllBySellerId(sellerId);
        for (Product product : products) {
            // Fetch product images associated with each product
            List<ProductImage> images = productImageRepository.findByProductId(product.getProductId());
            product.setImageUrls(images);  // Assuming 'imageUrls' is a List<ProductImage>
        }
        return products;
    }
    
    @Transactional
    public void deleteProduct(Long productId) {
        // Delete product images associated with the product
        productImageRepository.deleteImagesByProductId(productId);
        // Delete colors associated with the product (if applicable)
        colorRepository.deleteColorsByProductId(productId);
        // Finally, delete the product
        productRepository.deleteById(productId);
    }
    


    public Product findById(Long productId) {
        return productRepository.findById(productId).orElse(null); // Fetch product by ID
    }


    public void updateProduct(Product product) {
        productRepository.save(product);
    }
}
