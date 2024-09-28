package com.project.P1_Revshop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.P1_Revshop.model.Color;
import com.project.P1_Revshop.repository.Color_Repository;

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
}
