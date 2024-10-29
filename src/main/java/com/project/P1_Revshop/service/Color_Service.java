package com.project.P1_Revshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.P1_Revshop.exceptions.ResourceNotFoundException;
import com.project.P1_Revshop.model.Color;
import com.project.P1_Revshop.repository.Color_Repository;

import jakarta.transaction.Transactional;

@Service
public class Color_Service {
	@Autowired
    private Color_Repository colorRepository;

    public Color getColorById(Long colorId) {
        return colorRepository.findById(colorId).orElse(null);
    }

    public List<Color> getAllColors() {
        return colorRepository.findAll();
    }
    public Color addColor(Color color) {
        return colorRepository.save(color);  // Persisting the color entity
    }
    public List<Color> findColorsByProductId(Long productId) {
        return colorRepository.findByProductId(productId);
    }


    public void updateColor(Color color) {
        colorRepository.save(color);// save() also updates if the entity exists
        //colorRepository.modifyColor(color.getColorId(),color.getColorName(),color.getColorUrl(),color.getProduct().getProductId());
    }


    @Transactional
    public void deleteColor(Long colorId) {
        if (colorRepository.existsById(colorId)) {
            colorRepository.deleteById(colorId);  // Delete the color by ID
        } else {
            throw new ResourceNotFoundException("Color not found with ID: " + colorId);
        }
    }
}
