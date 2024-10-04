package com.project.P1_Revshop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.project.P1_Revshop.model.Color;



@Repository
public interface  Color_Repository extends JpaRepository< Color,Long> {

	@Modifying
    @Query(value = "DELETE FROM color WHERE product_id = :productId", nativeQuery = true)
    void deleteColorsByProductId(Long productId);
	
	@Query(value = "select color_id,color_name,color_url,product_id FROM color WHERE product_id = :productId", nativeQuery = true)
	 List<Color> findByProductId(Long productId);
	
	
	
	//@Query(value = "UPDATE color set color_name=:colorname,color_url=:colorurl,product_id=:productid WHERE color_id = :colorid", nativeQuery = true)
	//void modifyColor(Long  colorid,String colorname,String colorurl,Long productid);
}