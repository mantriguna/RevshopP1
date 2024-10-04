package com.project.P1_Revshop.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.P1_Revshop.DTO.SellerDTO;
import com.project.P1_Revshop.model.Brand;
import com.project.P1_Revshop.model.Category;
import com.project.P1_Revshop.model.Color;
import com.project.P1_Revshop.model.Product;
import com.project.P1_Revshop.model.ProductImage;
import com.project.P1_Revshop.service.Brand_Service;
import com.project.P1_Revshop.service.Category_Service;
import com.project.P1_Revshop.service.Color_Service;
import com.project.P1_Revshop.service.ProductImage_Service;
import com.project.P1_Revshop.service.Product_Service;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
    
    @Autowired
    private ProductImage_Service productImageService;
    
    @GetMapping("/editProduct/{productId}")
    public String showEditProductForm(HttpSession session,@PathVariable("productId") Long productId, Model model) {
    	Long sellerId = (Long) session.getAttribute("sellerId");
        Product product = productService.findById(productId);
        model.addAttribute("product", product);
        model.addAttribute("categories", categoryService. getAllCategories()); // Fetch categories
        model.addAttribute("brands", brandService.getBrandsByCategoryId(product.getCategory().getCategoryId())); // Fetch brands based on category
        return "Seller_UpdateProduct"; // Thymeleaf template name
    }
    
    
    
    
    @PostMapping("/updateProduct")
    public String updateProduct(
            @RequestParam("productId") Long productId,
            @RequestParam("productName") String productName,
            @RequestParam("description") String description,
            @RequestParam("price") double price,
            @RequestParam("threshold") int threshold,
            @RequestParam("maxDiscount") Double maxDiscount,
            @RequestParam("stockQuantity") int stockQuantity,
            @RequestParam(value = "colorNames") List<String> colorNames,
            @RequestParam(value = "colorUrls") List<String> colorUrls,
            @RequestParam(value = "imageUrls") List<String> imageUrls,
            @RequestParam("categoryId") Long categoryId,
            @RequestParam("brandId") Long brandId,
            Model model) {

        // Retrieve the existing product from the database
        Product product = productService.findProductById(productId);

        // Update the product fields
        product.setProductName(productName);
        product.setDescription(description);
        product.setPrice(price);
        product.setThreshold(threshold);
        product.setMaxDiscount(maxDiscount);
        product.setStockQuantity(stockQuantity);

        // Retrieve the category and brand from the database
        Category category = categoryService.findCategoryById(categoryId);
        product.setCategory(category);
        product.setBrand(brandService.getBrandById(brandId));

        // Update the product colors
        List<Color> existingColors = colorService.findColorsByProductId(productId);
        List<Color> updatedColors = new ArrayList<>();

        // Update existing colors or add new colors
        for (int i = 0; i < colorNames.size(); i++) {
            String colorName = colorNames.get(i);
            String colorUrl = colorUrls.get(i);

            // Check if the color exists
            if (i < existingColors.size()) {
                Color color = existingColors.get(i);
                color.setColorName(colorName);
                color.setColorUrl(colorUrl);
                color.setProduct(product);
                colorService.updateColor(color); // Update existing color
                updatedColors.add(color);
            } else {
                // Create new color if there's an extra one
                Color newColor = new Color(colorName, colorUrl);
                newColor.setProduct(product);
                colorService.addColor(newColor);
                updatedColors.add(newColor);
            }
        }

        product.setColors(updatedColors); // Associate updated colors with the product

        // Delete colors that are not present in the form submission
        for (int i = colorNames.size(); i < existingColors.size(); i++) {
            Color colorToDelete = existingColors.get(i);
            colorService.deleteColor(colorToDelete.getColorId());
        }

        // Update the product images
        List<ProductImage> existingImages = productImageService.findImagesByProductId(productId);
        List<ProductImage> updatedImages = new ArrayList<>();

        // Update existing images or add new images
        for (int i = 0; i < imageUrls.size(); i++) {
            String imageUrl = imageUrls.get(i);

            if (i < existingImages.size()) {
                // Update existing image
                ProductImage image = existingImages.get(i);
                image.setImageUrl(imageUrl);
                productImageService.updateProductImage(image); // Update existing image
                updatedImages.add(image);
            } else {
                // Create new image if there's an extra one
                ProductImage newImage = new ProductImage(imageUrl, category, product);
                productImageService.addProductImage(newImage);
                updatedImages.add(newImage);
            }
        }

        product.setImageUrls(updatedImages); // Associate updated images with the product

        // Delete images that are not present in the form submission
        for (int i = imageUrls.size(); i < existingImages.size(); i++) {
            ProductImage imageToDelete = existingImages.get(i);
            productImageService.deleteProductImage(imageToDelete.getImageId());
        }

        // Save the updated product
        productService.updateProduct(product);

        // Redirect after successful update
        return "redirect:/SellerProduct/showproducts";
    }

    @DeleteMapping("/deleteColor/{colorId}")
    @ResponseBody
    public ResponseEntity<?> deleteColor(@PathVariable Long colorId) {
        colorService.deleteColor(colorId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/deleteImage/{imageId}")
    @ResponseBody
    public ResponseEntity<?> deleteImage(@PathVariable Long imageId) {
        productImageService.deleteProductImage(imageId);
        return ResponseEntity.ok().build();
    }


    
    







    //deleting
    
    @DeleteMapping("/delete/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }
    
    //showing
    
    
    @GetMapping("/showproducts")
    public String showSellerProducts(HttpSession session, Model model) {
        // Retrieve seller information from the session
        Long sellerid = (Long) session.getAttribute("sellerId");
        if (sellerid == null) {
            return "redirect:/Seller/login";  // Redirect to login if seller not found in session
        }

        // Get the list of products for the seller
        List<Product> productList = productService.findProductsBySellerId(sellerid);
        
        // Add the list of products to the model
        model.addAttribute("productList", productList);

        // Return the Thymeleaf template for displaying seller products
        return "SellerShowProducts";  // This should match your Thymeleaf template name
    }
    
    //adding 
    
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
    public String addProduct(HttpSession session,@RequestParam("productName") String productName,
                             @RequestParam("description") String description,
                             @RequestParam("price") double price,
                             @RequestParam("threshold") int threshold,
                             @RequestParam("discount") Double maxDiscount,
                             @RequestParam("imageUrls") List<String> imageUrls,
                             @RequestParam("colorNames") List<String> colorNames,  // Accept color names as List<String>
                             @RequestParam("colorUrls") List<String> colorUrls,   // Accept color URLs as List<String>
                             @RequestParam("brand") Long brandId,
                             @RequestParam("category") Category category,
                             @RequestParam("stockQuantity") int stockQuantity,  // Accept stock quantity
                             Model model) {
    	 Long sellerId = (Long) session.getAttribute("sellerId");
        // Retrieve brand by ID
        Brand brand = brandService.getBrandById(brandId);

        // Create Product object using constructor
        Product product = new Product(
                category,
                sellerId,
                productName,
                description,
                price,
                stockQuantity,
                threshold,
                maxDiscount,
                brand);

        // Save the product first (so we can use it for image and color association)
        productService.addProduct(product);

        // Save the colors with names and URLs
        List<Color> productColors = new ArrayList<>();
        for (int i = 0; i < colorNames.size(); i++) {
            String colorName = colorNames.get(i);
            String colorUrl = colorUrls.get(i);  // Get corresponding color URL
            Color color = new Color(colorName, colorUrl);  // Create Color with both name and URL
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

        //return "redirect:/SellerProduct/success";
        return "redirect:/Seller/main";
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