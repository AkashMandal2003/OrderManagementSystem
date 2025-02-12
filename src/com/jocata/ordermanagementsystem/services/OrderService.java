package com.jocata.ordermanagementsystem.services;

import com.jocata.ordermanagementsystem.forms.CustomerForm;
import com.jocata.ordermanagementsystem.forms.OrderForm;
import com.jocata.ordermanagementsystem.forms.ProductForm;

import java.util.List;

public interface OrderService {
    void createOrder(CustomerForm customer, List<ProductForm> products);

    List<OrderForm> getCustomerAllOrders(Integer customerId);

    void updateOrder(int orderId, OrderForm updatedOrderForm);

    void cancelOrder(int orderId);
}
