package com.jocata.ordermanagementsystem.daos.impl;

import com.jocata.ordermanagementsystem.daos.OrderDao;
import com.jocata.ordermanagementsystem.db.InMemoryOrders;
import com.jocata.ordermanagementsystem.entities.OrderDetails;

import java.util.List;

public class OrderDaoImpl implements OrderDao {
    @Override
    public OrderDetails createOrder(OrderDetails orderDetails) {
        InMemoryOrders.persist(orderDetails);
        return orderDetails;
    }

    @Override
    public OrderDetails getOrderById(Integer orderId) {
        return InMemoryOrders.read(orderId);
    }

    @Override
    public List<OrderDetails> allOrders() {
        return InMemoryOrders.getAllOrders();
    }

    @Override
    public List<OrderDetails> customersAllOrders(Integer customerId) {
        return InMemoryOrders.findOrdersByCustomerId(customerId);
    }

    @Override
    public void updateOrder(OrderDetails existingOrder) {
        InMemoryOrders.updateOrder(existingOrder.getOrderId(),existingOrder);
    }

    @Override
    public void cancelOrder(OrderDetails orderDetails) {
        InMemoryOrders.cancelOrder(orderDetails.getOrderId());
    }


}
