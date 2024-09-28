package com.project.P1_Revshop.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.project.P1_Revshop.DTO.SellerDTO;
import com.project.P1_Revshop.model.Brand;
import com.project.P1_Revshop.model.Category;
import com.project.P1_Revshop.model.Color;
import com.project.P1_Revshop.model.Product;
import com.project.P1_Revshop.model.ProductImage;
import com.project.P1_Revshop.service.Brand_Service;
import com.project.P1_Revshop.service.Category_Service;
import com.project.P1_Revshop.service.Color_Service;
import com.project.P1_Revshop.service.Product_Service;

@Controller
@RequestMapping("/SellerProduct")
public class SellerProduct_Controller {
    @Autowired
    private Product_Service productService;

    @Autowired
    private Category_Service categoryService;

    @Autowired
    private Color_Service colorService;

    @Autowired
    private Brand_Service brandService;

    @GetMapping("/addProduct")
    public String showAddProductForm(Model model) {

         // Add brands to model
        model.addAttribute("product", new Product());
        // Fetch available categories from the database
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);

        // Fetch available colors from the database
        List<Color> colors = colorService.getAllColors();
        model.addAttribute("colors", colors);

        // Fetch available brands from the database
        List<Brand> brands = brandService.getAllBrands();  // Fetch brands
        model.addAttribute("brands", brands);
        
        return "Seller_AddProduct";
    }

    @PostMapping("/addProduct")
    public String addProduct(@RequestParam("productName") String productName,
                             @RequestParam("description") String description,
                             @RequestParam("price") double price,
                             @RequestParam("threshold") int threshold,
                             @RequestParam("discount") Double maxDiscount,
                             @RequestParam("imageUrls") List<String> imageUrls,
                             @RequestParam("colors") List<String> colorNames,  // Accept color names as List<String>
                             @RequestParam("brand") Long brandId, 
                             @RequestParam("category") Category category,
                             @RequestParam("stockQuantity") int stockQuantity,  // Accept stock quantity
                             Model model) {
    	System.out.println(productName+description+price+threshold+maxDiscount+imageUrls.toString()+colorNames.toString()+brandId+category+stockQuantity);
        // Retrieve brand by ID
        Brand brand = brandService.getBrandById(brandId);
        // Create Product object using constructor
        Product product = new Product(
                category, 
                1L,
                productName, 
                description, 
                price, 
                stockQuantity, 
                threshold, 
                maxDiscount, 
                brand);

        // Save the product first (so we can use it for image and color association)
        productService.addProduct(product);

        // Save the colors
        List<Color> productColors = new ArrayList<>();
        for (String colorName : colorNames) {
            Color color = new Color(colorName);
            colorService.addColor(color);  // Save to Color table
            productColors.add(color);
        }
        product.setColors(productColors);  // Associate colors with the product

        // Save the product images with associated colors
        List<ProductImage> productImages = new ArrayList<>();
        for (String imageUrl : imageUrls) {
            ProductImage productImage = new ProductImage(imageUrl, category, product);
            productImages.add(productImage);
        }
        product.setImageUrls(productImages);  // Associate image URLs with the product

        // Finally save everything with the updated associations
        productService.addProduct(product);

        return "redirect:/SellerProduct/success";
    }

    @GetMapping("/brands")
    @ResponseBody  // Add this to return JSON response
    public List<Brand> getBrandsByCategory(@RequestParam("categoryId") Long categoryId) {
        if (categoryId != null) {
            return brandService.getBrandsByCategoryId(categoryId);
        }
        return new ArrayList<>(); // Return an empty list if no category is provided
    }

}
