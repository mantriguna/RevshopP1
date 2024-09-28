package com.project.P1_Revshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.P1_Revshop.model.Color;


@Repository
public interface  Color_Repository extends JpaRepository< Color,Long> {

}