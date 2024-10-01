package com.project.P1_Revshop.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
import com.project.P1_Revshop.service.Product_Service;

import jakarta.servlet.http.HttpSession;
import org.springframework.http.HttpStatus;

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
    
//    @GetMapping("/editProduct/{productId}")
//    public String showEditProductForm(HttpSession session,@PathVariable Long productId, Model model) {
//        Product product = productService.findProductById(productId);
//        
//        List<Category> categories = categoryService.getAllCategories();
//        Long sellerId = (Long) session.getAttribute("sellerId");
//        model.addAttribute("sellerId", sellerId);
//        model.addAttribute("product", product);
//        model.addAttribute("categories", categories);
//        return "Seller_UpdateProduct"; // Thymeleaf template name
//    }
    @GetMapping("/editProduct/{productId}")
    public String showEditProductForm(HttpSession session,@PathVariable("productId") Long productId, Model model) {
    	Long sellerId = (Long) session.getAttribute("sellerId");
        Product product = productService.findById(productId);
        model.addAttribute("product", product);
        model.addAttribute("categories", productService.findAllCategories()); // Fetch categories
        model.addAttribute("brands", productService.findBrandsByCategory(product.getCategory().getCategoryId())); // Fetch brands based on category
        return "Seller_UpdateProduct"; // Thymeleaf template name
    }

    // Handle the submission of the updated product
    @PostMapping("/updateProduct")
    public String updateProduct(@ModelAttribute Product product, RedirectAttributes redirectAttributes) {
        productService.updateProduct(product);
        redirectAttributes.addFlashAttribute("message", "Product updated successfully!");
        return "redirect:/SellerProduct/showproducts"; // Redirect to product list or another page
    }

    @DeleteMapping("/delete/{productId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable Long productId) {
        productService.deleteProduct(productId);
    }
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