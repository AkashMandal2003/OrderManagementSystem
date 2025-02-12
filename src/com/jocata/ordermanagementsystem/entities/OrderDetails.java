package com.jocata.ordermanagementsystem.entities;

import com.jocata.ordermanagementsystem.util.OrderStatus;

import java.util.List;

public class OrderDetails {
    private Integer orderId;
    private CustomerDetails customer;
    private List<ProductDetails> products;
    private double totalAmount;
    private OrderStatus status;

    private double calculateTotal() {
        double total = 0.0;
        for (ProductDetails product : products) {
            total += product.getProductPrice();
        }
        return total;
    }

    public int getOrderId() { return orderId; }
    public void setOrderId(Integer orderId) { this.orderId = orderId; }

    public CustomerDetails getCustomer() { return customer; }
    public void setCustomer(CustomerDetails customer) { this.customer = customer; }

    public List<ProductDetails> getProducts() { return products; }
    public void setProducts(List<ProductDetails> products) {
        this.products = products;
        this.totalAmount = calculateTotal();
    }

    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }

    public OrderStatus getStatus() { return status; }
    public void setStatus(OrderStatus status) { this.status = status; }
}
