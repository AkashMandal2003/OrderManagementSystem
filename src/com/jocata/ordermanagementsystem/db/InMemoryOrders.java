package com.jocata.ordermanagementsystem.db;

import com.jocata.ordermanagementsystem.entities.OrderDetails;
import com.jocata.ordermanagementsystem.entities.CustomerDetails;
import com.jocata.ordermanagementsystem.entities.ProductDetails;
import com.jocata.ordermanagementsystem.util.OrderStatus;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InMemoryOrders {

    private static final Logger logger = Logger.getLogger(InMemoryOrders.class.getName());
    private static final String FILE_NAME = "orders.csv";
    private static final String DELIMITER = ",";

    private static final Map<Integer, OrderDetails> orderMap = new ConcurrentHashMap<>();
    private static int orderCounter = 1;

    static {
        loadDataFromFile();
    }

    public static void loadDataFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            logger.info("No previous order data found. Starting with an empty map.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(DELIMITER);
                if (parts.length >= 5) {
                    OrderDetails order = new OrderDetails();
                    order.setOrderId(Integer.parseInt(parts[0]));
                    CustomerDetails customer = new CustomerDetails();
                    customer.setCustomerId(Integer.parseInt(parts[1]));
                    customer.setCustomerName(parts[2]);
                    customer.setEmail(parts[3]);
                    order.setCustomer(customer);
                    List<ProductDetails> productList = new ArrayList<>();
                    String[] productParts = parts[4].split(";");
                    for (String productData : productParts) {
                        String[] productInfo = productData.split("-");
                        if (productInfo.length == 3) {
                            ProductDetails product = new ProductDetails();
                            product.setProductId(Integer.parseInt(productInfo[0]));
                            product.setProductName(productInfo[1]);
                            product.setProductPrice(Double.parseDouble(productInfo[2]));
                            productList.add(product);
                        }
                    }
                    order.setProducts(productList);

                    order.setTotalAmount(Double.parseDouble(parts[5]));
                    order.setStatus(OrderStatus.valueOf(parts[6]));

                    orderMap.put(order.getOrderId(), order);
                    orderCounter = Math.max(orderCounter, order.getOrderId() + 1);
                }
            }
        } catch (IOException | NumberFormatException e) {
            logger.log(Level.SEVERE, "Error loading order data from CSV", e);
        }
    }

    public static void saveDataToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (OrderDetails order : orderMap.values()) {
                StringBuilder productData = new StringBuilder();
                for (ProductDetails product : order.getProducts()) {
                    productData.append(product.getProductId()).append("-")
                            .append(product.getProductName()).append("-")
                            .append(product.getProductPrice()).append(";");
                }

                writer.println(order.getOrderId() + DELIMITER +
                        order.getCustomer().getCustomerId() + DELIMITER +
                        order.getCustomer().getCustomerName() + DELIMITER +
                        order.getCustomer().getEmail() + DELIMITER +
                        productData.toString() + DELIMITER +
                        order.getTotalAmount() + DELIMITER +
                        order.getStatus());
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error saving order data to CSV", e);
        }
    }

    public static void persist(OrderDetails order) {
        if (order.getOrderId() == 0) {
            order.setOrderId(orderCounter++);
        }
        orderMap.put(order.getOrderId(), order);
        saveDataToFile();
    }

    public static OrderDetails read(Integer orderId) {
        return orderMap.get(orderId);
    }

    public static List<OrderDetails> getAllOrders() {
        return new ArrayList<>(orderMap.values());
    }

    public static void updateOrderStatus(int orderId, OrderStatus newStatus) {
        OrderDetails order = orderMap.get(orderId);
        if (order != null) {
            order.setStatus(newStatus);
            saveDataToFile();
            logger.info("Order ID " + orderId + " status updated to " + newStatus);
        } else {
            logger.warning("Order ID " + orderId + " not found.");
        }
    }

    public static void updateOrder(int orderId, OrderDetails updatedOrder) {
        if (orderMap.containsKey(orderId)) {
            updatedOrder.setOrderId(orderId);
            orderMap.put(orderId, updatedOrder);
            saveDataToFile();
            logger.info("Order ID " + orderId + " updated successfully.");
        } else {
            logger.warning("Order ID " + orderId + " not found.");
        }
    }

    public static void cancelOrder(int orderId) {
        if (orderMap.containsKey(orderId)) {
            orderMap.get(orderId).setStatus(OrderStatus.CANCELED);
            saveDataToFile();
            logger.info("Order ID " + orderId + " has been canceled.");
        } else {
            logger.warning("Order ID " + orderId + " not found.");
        }
    }

    public static List<OrderDetails> findOrdersByCustomerId(int customerId) {
        List<OrderDetails> result = new ArrayList<>();
        for (OrderDetails order : orderMap.values()) {
            if (order.getCustomer().getCustomerId() == customerId) {
                result.add(order);
            }
        }
        return result;
    }

}
