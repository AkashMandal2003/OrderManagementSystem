package com.jocata.ordermanagementsystem.forms;

import java.util.List;

public class OrderForm {
    private CustomerForm customer;
    private List<ProductForm> products;

    public CustomerForm getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerForm customer) {
        this.customer = customer;
    }

    public List<ProductForm> getProducts() {
        return products;
    }

    public void setProducts(List<ProductForm> products) {
        this.products = products;
    }
}
