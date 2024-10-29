package com.project.P1_Revshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.P1_Revshop.exceptions.ResourceNotFoundException;
import com.project.P1_Revshop.model.ProductImage;
import com.project.P1_Revshop.repository.ProductImage_Repository;

import jakarta.transaction.Transactional;

@Service
public class ProductImage_Service {
	@Autowired
    private ProductImage_Repository productImageRepository;
	

	public ProductImage findImageById(Long imageId) {
        return productImageRepository.findById(imageId)
            .orElseThrow(() -> new RuntimeException("Product image not found with id: " + imageId));
    }
	
    public List<ProductImage> findImagesByProductId(Long productId) {
        return productImageRepository.findByProductId(productId);
    }

    public void addProductImage(ProductImage productImage) {
        productImageRepository.save(productImage);
    }

    public void updateProductImage(ProductImage productImage) {
        productImageRepository.save(productImage); // save() also updates if the entity exists
    }


    @Transactional
    public void deleteProductImage(Long imageId) {
        if (productImageRepository.existsById(imageId)) {
            productImageRepository.deleteById(imageId);  // Delete the image by ID
        } else {
            throw new ResourceNotFoundException("Image not found with ID: " + imageId);
        }
    }
}
