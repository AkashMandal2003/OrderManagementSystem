package com.jocata.ordermanagementsystem.controllers;

import com.jocata.ordermanagementsystem.forms.OrderForm;
import com.jocata.ordermanagementsystem.services.OrderService;
import com.jocata.ordermanagementsystem.services.impl.OrderServiceImpl;

import java.util.List;

public class OrderController {

     OrderService orderService=new OrderServiceImpl();

    public void createOrder(OrderForm orderForm) {
        if(orderForm!=null) {
            orderService.createOrder(orderForm.getCustomer(), orderForm.getProducts());
            return;
        }
        throw new IllegalArgumentException("Details are missing..");
    }

    public void updateOrder(Integer orderId,OrderForm orderForm){
        if(orderId!=null && orderForm!=null){
            orderService.updateOrder(orderId,orderForm);
            return;
        }
        throw new IllegalArgumentException("Details are missing..");
    }

    public void cancelOrder(Integer orderId){
        if(orderId!=null){
            orderService.cancelOrder(orderId);
            return;
        }
        throw new IllegalArgumentException("Details are missing..");
    }

    public List<OrderForm> getCustomerAllOrders(OrderForm orderForm){
        return orderService.getCustomerAllOrders(Integer.valueOf(orderForm.getCustomer().getCustomerId()));
    }

}
