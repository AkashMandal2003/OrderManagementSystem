package com.jocata.ordermanagementsystem;

import com.jocata.ordermanagementsystem.controllers.CustomerController;
import com.jocata.ordermanagementsystem.controllers.OrderController;
import com.jocata.ordermanagementsystem.controllers.ProductController;
import com.jocata.ordermanagementsystem.forms.CustomerForm;
import com.jocata.ordermanagementsystem.forms.OrderForm;
import com.jocata.ordermanagementsystem.forms.ProductForm;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        OrderController orderController = new OrderController();
        CustomerController customerController = new CustomerController();
        ProductController productController = new ProductController();

        while (true) {
            logger.info("Select an option:");
            logger.info("1. Manage Orders");
            logger.info("2. Manage Customers");
            logger.info("3. Manage Products");
            logger.info("-1. Exit");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    manageOrders(scanner, customerController, productController, orderController);
                    break;
                case 2:
                    manageCustomers(scanner, customerController);
                    break;
                case 3:
                    manageProducts(scanner, productController);
                    break;
                case -1:
                    logger.info("Exiting program...");
                    return;
                default:
                    logger.info("Invalid option. Please try again.");
            }
        }
    }

    private static void manageOrders(Scanner scanner, CustomerController customerController, ProductController productController, OrderController orderController) {
        logger.info("1. Create Orders");
        logger.info("2. View Orders");
        logger.info("3. Update Order");
        logger.info("4. Cancel Order");
        logger.info("Select an option: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                createOrders(scanner, customerController, productController, orderController);
                break;
            case 2:
                viewOrdersUsingCustomerId(scanner, customerController, orderController);
                break;
            case 3:
                updateOrder(scanner, customerController, productController, orderController);
                break;
            case 4:
                cancelOrder(scanner, orderController);
                break;
            default:
                logger.info("Invalid option.");
        }
    }

    private static void createOrders(Scanner scanner, CustomerController customerController, ProductController productController, OrderController orderController) {
        logger.info("Enter customer ID: ");
        int customerId = scanner.nextInt();
        CustomerForm customer = customerController.getCustomer(customerId);
        if (customer == null) {
            logger.info("Customer not found!");
            return;
        }

        logger.info("Enter product IDs (comma-separated): ");
        String[] productIds = scanner.next().split(",");
        List<ProductForm> products = new ArrayList<>();
        for (String id : productIds) {
            ProductForm product = productController.getProduct(Integer.parseInt(id));
            if (product != null) {
                products.add(product);
            } else {
                logger.info("Product with ID " + id + " not found!");
            }
        }

//        for (int i = 0; i < 5; i++) {
            OrderForm orderForm = new OrderForm();
            orderForm.setCustomer(customer);
            orderForm.setProducts(products);
            orderController.createOrder(orderForm);
            logger.info("Order created successfully!");
//        }
    }

    private static void viewOrdersUsingCustomerId(Scanner scanner, CustomerController customerController, OrderController orderController) {
        logger.info("Enter customer ID to view all orders: ");
        int customerId = scanner.nextInt();
        CustomerForm customer = customerController.getCustomer(customerId);
        if (customer == null) {
            logger.info("Customer not found!");
            return;
        }
        OrderForm orderForm=new OrderForm();
        orderForm.setCustomer(customer);
        List<OrderForm> customerAllOrders = orderController.getCustomerAllOrders(orderForm);
        for (OrderForm form:customerAllOrders){
            logger.info(form.getOrderId()+",");
            for (ProductForm product : form.getProducts()) {
                logger.info(product.getProductName());
            }
        }
    }

    private static void updateOrder(Scanner scanner, CustomerController customerController, ProductController productController, OrderController orderController) {
        logger.info("Enter order ID to update: ");
        int orderId = scanner.nextInt();
        OrderForm existingOrder = orderController.getOrderById(orderId);
        if (existingOrder != null) {
            logger.info("Updating order with ID: " + existingOrder.getOrderId());
            logger.info("Enter new product ID to add to the order: ");
            int newProductId = scanner.nextInt();
            ProductForm newProduct = productController.getProduct(newProductId);
            if (newProduct != null) {
                existingOrder.getProducts().add(newProduct);
                orderController.updateOrder(existingOrder);
                logger.info("Order updated successfully!");
            } else {
                logger.info("Product not found!");
            }
        } else {
            logger.info("Order not found for update!");
        }
    }

    private static void cancelOrder(Scanner scanner, OrderController orderController) {
        logger.info("Enter order ID to cancel: ");
        int cancelOrderId = scanner.nextInt();
        OrderForm orderToCancel = orderController.getOrderById(cancelOrderId);
        if (orderToCancel != null) {
            orderController.cancelOrder(cancelOrderId);
            logger.info("Order cancelled successfully!");
        } else {
            logger.info("Order not found for cancellation!");
        }
    }


    private static void manageCustomers(Scanner scanner, CustomerController customerController) {
        logger.info("1. Add New Customer");
        logger.info("2. Update Customer");
        logger.info("3. Delete Customer");
        logger.info("Select an option: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                addCustomer(scanner, customerController);
                break;
            case 2:
                updateCustomer(scanner, customerController);
                break;
            case 3:
                deleteCustomer(scanner, customerController);
                break;
            default:
                logger.info("Invalid option.");
        }
    }

    private static void addCustomer(Scanner scanner, CustomerController customerController) {
        scanner.nextLine();
        CustomerForm customer = new CustomerForm();
        logger.info("Enter customer name: ");
        customer.setCustomerName(scanner.nextLine());
        logger.info("Enter email: ");
        customer.setEmail(scanner.nextLine());
        logger.info("Enter password: ");
        customer.setPassword(scanner.nextLine());
        logger.info("Enter address: ");
        customer.setAddress(scanner.nextLine());

        CustomerForm customerForm = customerController.saveCustomer(customer);
        logger.info("Customer added with ID: " + customerForm.getCustomerId());
    }

    private static void updateCustomer(Scanner scanner, CustomerController customerController) {
        logger.info("Enter customer ID to update: ");
        int customerId = scanner.nextInt();
        CustomerForm customer = customerController.getCustomer(customerId);
        if (customer != null) {
            scanner.nextLine();
            logger.info("Enter new customer name: ");
            customer.setCustomerName(scanner.nextLine());
            logger.info("Enter new email: ");
            customer.setEmail(scanner.nextLine());
            logger.info("Enter new password: ");
            customer.setPassword(scanner.nextLine());
            logger.info("Enter new address: ");
            customer.setAddress(scanner.nextLine());

            customerController.updateCustomer(customer);
            logger.info("Customer updated successfully!");
        } else {
            logger.info("Customer not found!");
        }
    }

    private static void deleteCustomer(Scanner scanner, CustomerController customerController) {
        logger.info("Enter customer ID to delete: ");
        int customerId = scanner.nextInt();
        String result = customerController.deleteCustomer(customerId);
        logger.info(result);
    }

    private static void manageProducts(Scanner scanner, ProductController productController) {
        logger.info("1. Add New Product");
        logger.info("2. Update Product");
        logger.info("3. Delete Product");
        logger.info("4. View Product");
        logger.info("Select an option: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                addProduct(scanner, productController);
                break;
            case 2:
                updateProduct(scanner, productController);
                break;
            case 3:
                deleteProduct(scanner, productController);
                break;
            case 4:
                viewProduct(scanner, productController);
                break;
            default:
                logger.info("Invalid option.");
        }
    }

    private static void addProduct(Scanner scanner, ProductController productController) {
        scanner.nextLine();
        ProductForm product = new ProductForm();
        logger.info("Enter product name: ");
        product.setProductName(scanner.nextLine());
        logger.info("Enter product price: ");
        product.setProductPrice(String.valueOf(scanner.nextDouble()));
        logger.info("Enter product Stock: ");
        product.setProductInStock(String.valueOf(scanner.nextInt()));

        ProductForm productForm = productController.saveProduct(product);
        logger.info("Product added with ID: " + productForm.getProductId());
    }

    private static void updateProduct(Scanner scanner, ProductController productController) {
        logger.info("Enter product ID to update: ");
        int productId = scanner.nextInt();
        ProductForm product = productController.getProduct(productId);
        if (product != null) {
            scanner.nextLine();
            logger.info("Enter new product name: ");
            product.setProductName(scanner.nextLine());
            logger.info("Enter new product price: ");
            product.setProductPrice(String.valueOf(scanner.nextDouble()));
            logger.info("Enter product Stock: ");
            product.setProductInStock(String.valueOf(scanner.nextInt()));

            productController.updateProduct(product);
            logger.info("Product updated successfully!");
        } else {
            logger.info("Product not found!");
        }
    }

    private static void deleteProduct(Scanner scanner, ProductController productController) {
        logger.info("Enter product ID to delete: ");
        int productId = scanner.nextInt();
        productController.deleteProduct(productId);
        logger.info("Product Deleted");
    }

    private static void viewProduct(Scanner scanner, ProductController productController) {
        logger.info("Enter product ID to view details: ");
        int productId = scanner.nextInt();
        ProductForm product = productController.getProduct(productId);

        if (product != null) {
            logger.info("Product ID: " + product.getProductId());
            logger.info("Product Name: " + product.getProductName());
            logger.info("Product Price: " + product.getProductPrice());
            logger.info("Product Stock: " + product.getProductInStock());
        } else {
            logger.info("Product not found!");
        }
    }
}
