package com.jocata.ordermanagementsystem.db;

import com.jocata.ordermanagementsystem.entities.ProductDetails;

import java.io.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InMemoryProduct {

    private static final Logger logger = Logger.getLogger(InMemoryProduct.class.getName());
    private static final String FILE_NAME = "products.csv";
    private static final String DELIMITER = ",";

    private static final Map<Integer, ProductDetails> loginMap = new ConcurrentHashMap<>();

    static {
        loadDataFromFile();
    }

    public static void loadDataFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            logger.info("No previous data found. Starting with an empty map.");
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(DELIMITER);
                if (parts.length >= 4) {
                    ProductDetails product = new ProductDetails();
                    product.setProductId(Integer.parseInt(parts[0]));
                    product.setProductName(parts[1]);
                    product.setProductPrice(Double.valueOf(parts[2]));
                    product.setProductInStock(Integer.valueOf(parts[3]));

                    loginMap.put(product.getProductId(), product);
                }
            }
        } catch (IOException | NumberFormatException e) {
            logger.log(Level.SEVERE, "Error loading product data from CSV", e);
        }
    }

    public static void saveDataToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (ProductDetails product : loginMap.values()) {
                writer.println(product.getProductId() + DELIMITER +
                        product.getProductName() + DELIMITER +
                        product.getProductPrice() + DELIMITER +
                        product.getProductInStock());
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error saving product data to CSV", e);
        }
    }

    public static void persist(ProductDetails product) {
        loginMap.put(product.getProductId(), product);
        saveDataToFile();
    }

    public static ProductDetails read(Integer productId) {
        return loginMap.get(productId);
    }

    public static List<ProductDetails> getAllProducts() {
        return new ArrayList<>(loginMap.values());
    }

    public static void update(ProductDetails product) {
        if (loginMap.containsKey(product.getProductId())) {
            loginMap.put(product.getProductId(), product);
            saveDataToFile();
        } else {
            logger.info("Entry with ID " + product.getProductId() + " not found.");
        }
    }

    public static void delete(Integer productId) {
        if (loginMap.remove(productId) != null) {
            saveDataToFile();
        } else {
            logger.info("Not Found");
        }
    }
}
