package com.jocata.ordermanagementsystem.test;

import com.jocata.ordermanagementsystem.controllers.CustomerController;
import com.jocata.ordermanagementsystem.controllers.OrderController;
import com.jocata.ordermanagementsystem.controllers.ProductController;
import com.jocata.ordermanagementsystem.forms.CustomerForm;
import com.jocata.ordermanagementsystem.forms.OrderForm;
import com.jocata.ordermanagementsystem.forms.ProductForm;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class OrderMain {

    private static final Logger logger= Logger.getLogger(OrderMain.class.getName());

    public static void main(String[] args) {

        OrderController orderController = new OrderController();
        //CustomerController customerController=new CustomerController();
//        ProductController productController=new ProductController();
//
        //CustomerForm customer = customerController.getCustomer(481063);
//        if (customer == null) {
//            logger.info("Customer not found!");
//            return;
//        }
//
//        ProductForm product1 = productController.getProduct(454789);
//        ProductForm product2 = productController.getProduct(713756);
//        if (product1 == null || product2 == null) {
//            logger.info("One or more products not found!");
//            return;
//        }
//
//        List<ProductForm> products=new ArrayList<>();
//        products.add(product1);
//        products.add(product2);
//
//        for (int i = 0; i < 5; i++) {
//            OrderForm orderForm = new OrderForm();
//            orderForm.setCustomer(customer);
//            orderForm.setProducts(products);
//            orderController.createOrder(orderForm);
//
//            logger.info("Order " + (i + 1) + " created successfully!");
//        }

//        OrderForm orderForm=new OrderForm();
//        orderForm.setCustomer(customer);
//        List<OrderForm> customerAllOrders = orderController.getCustomerAllOrders(orderForm);
//
//        for (OrderForm order : customerAllOrders) {
//            System.out.println(order.getCustomer().getCustomerName());
//        }

        orderController.cancelOrder(5);

    }
}
