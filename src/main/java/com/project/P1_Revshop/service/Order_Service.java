package com.project.P1_Revshop.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.project.P1_Revshop.model.Cart;
import com.project.P1_Revshop.model.Customer;
import com.project.P1_Revshop.model.Order;
import com.project.P1_Revshop.model.Order_Detail;
import com.project.P1_Revshop.model.Product;
import com.project.P1_Revshop.model.Seller;
import com.project.P1_Revshop.repository.OrderDetail_Repository;
import com.project.P1_Revshop.repository.Order_Repository;

import jakarta.transaction.Transactional;

@Service
public class Order_Service {
	
	@Autowired
    private Order_Repository orderRepository;
	
	@Autowired
    private Customer_Service customerService;
	@Autowired
    private OrderDetail_Service orderdetailService;
	
	@Autowired
    private Cart_Service cartService;
	
	@Autowired
    private Product_Service productService;
	
	@Autowired
    private Seller_Service sellerService;
	
	@Autowired
    private   Email_Service emailService;
	
	@Transactional
	public void placeOrder(Customer customer, double totalValue, String deliveryAddress) throws Exception {

        // Deduct the wallet balance
        double updatedWallet = customer.getWalletBalance() - totalValue;
        customer.setWalletBalance(updatedWallet);
        customerService.updateCustomerWallet(updatedWallet, customer.getCustomerId());

        // Create new order and add to database
        Order order=createOrder(customer, totalValue, deliveryAddress);
        
        List<Cart> cartItems = cartService.getCartItemsByCustomer(customer.getCustomerId());
        double grandTotal = 0;
        StringBuilder message = new StringBuilder();
        
        message.append("Hello ").append(customer.getName()).append(",\n");
        message.append("\nYour order has been placed successfully!\n\n Here are the details:\n\n");
        message.append("Delivery Address: ").append(deliveryAddress).append("\n\n");
        message.append("\nOrder Details:\n");
        message.append(String.format("%-20s %-10s %-10s %-15s\n\n\n\n", "Product Name","color", "Quantity", "Price/Unit", "Total"));

        for (Cart cartItem : cartItems) {
            Product product = cartItem.getProduct();
            
            double discountedPrice = product.getPrice() - ((product.getPrice() * product.getMaxDiscount()) / 100);
            double total = discountedPrice * cartItem.getQuantity();
            grandTotal += total;
            /*this.order = order;
        this.product = product;
        this.quantity = quantity;
        this.price_per_unit = price_per_unit;
        this.seller = seller;
        this.status = status;*/
            Seller seller = sellerService.getSellerByIdF(product.getSellerId());
            seller.setCurrentMonthEarning(seller.getCurrentMonthEarning()+total);
            seller.setTotalEarning(seller.getTotalEarning()+total);
            seller.setCurrentMonthItemSold(seller.getCurrentMonthItemSold()+cartItem.getQuantity());
            seller.setTotalItemSold(seller.getTotalItemSold()+cartItem.getQuantity());
            sellerService.updateSeller(seller);
            Order_Detail orderDetails = new Order_Detail(order, product, cartItem.getQuantity(), discountedPrice, seller,"Placed",cartItem.getColor());
            orderdetailService.addOrderDetails(orderDetails);

            // Update product stock
            int updatedStock = product.getStockQuantity() - cartItem.getQuantity();
            product.setStockQuantity(updatedStock);
            productService.updateProductStock(product);

            // Clear cart after order
            cartService.removeCartItem(cartItem.getCartId());

            // Append to email message
            message.append(String.format("%-20s %-10s %-10d %-15.2f %-15.2f\n", 
                    product.getProductName(), 
                    cartItem.getColor().getColorName(), 
                    cartItem.getQuantity(), 
                    discountedPrice, 
                    total));

            // Notify seller if stock is low
            if (product.getStockQuantity() <= product.getThreshold()) {
                notifySeller(product);
            }
        }
        
        // Send email to customer
        emailService.sendEmail(customer.getEmail(), "Order Placed Successfully!", message.toString());
    }

    private void notifySeller(Product product) {
        Seller seller = sellerService.getSellerByIdF(product.getSellerId());
        String subject = "Low Stock Alert for Product ID: " + product.getProductId();
        StringBuilder notification = new StringBuilder();
        
        notification.append("Dear ").append(seller.getName()).append(",\n");
        notification.append("\nThe stock for Product ID: ").append(product.getProductId()).append(" (").append(product.getProductName()).append(") is running low.\n");
        notification.append("\nCurrent stock: ").append(product.getStockQuantity()).append("\nPlease restock soon to meet demand.\n\n-Revshop");

        emailService.sendEmail(seller.getEmail(), subject, notification.toString());
    }
    
    
    public Order createOrder(Customer customer, double totalValue, String deliveryAddress) {
        Order order = new Order();
        order.setCustomer(customer);
        order.setTotal_amount(totalValue);
        order.setDelivery_address(deliveryAddress);
        order.setOrder_date(new Timestamp(System.currentTimeMillis()));


        return orderRepository.save(order);
    }
    
    
    

}
